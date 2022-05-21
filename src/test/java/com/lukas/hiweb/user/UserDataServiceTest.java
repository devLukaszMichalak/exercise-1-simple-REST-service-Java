package com.lukas.hiweb.user;

import com.lukas.hiweb.user.exceptions.DivideByZeroException;
import com.lukas.hiweb.user.exceptions.UserNotFoundException;
import com.lukas.hiweb.user.loginstatistics.LoginStatistics;
import com.lukas.hiweb.user.loginstatistics.LoginStatisticsRepository;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.verification.VerificationMode;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


class UserDataServiceTest {

    private LoginStatisticsRepository mockedLoginStatisticsRepository;

    private RestTemplate mockedRestTemplate;
    private UserDataService userDataService;

    @BeforeEach
    void setUp() {
        mockedRestTemplate = mock(RestTemplate.class);
        mockedLoginStatisticsRepository = mock(LoginStatisticsRepository.class);

        userDataService = new UserDataService(mockedLoginStatisticsRepository, mockedRestTemplate);
    }


    @Test
    void willFindExpectedUser() {
        //given
        String expectedLogin = "tester";
        UserData expectedUser = new UserData(1L, expectedLogin, "Test", "www.avatar.url", 4, 2, "now", null);

        //when
        when(mockedRestTemplate.getForObject(anyString(), any())).thenReturn(expectedUser);

        UserData foundUser = userDataService.getUserData(expectedLogin);

        //then
        assertThat(foundUser).isEqualTo(expectedUser);
    }


    @Test
    void willThrowZeroFollowersException() {
        //given
        String expectedLogin = "tester";
        UserData expectedUser = new UserData(1L, expectedLogin, "Test", "www.avatar.url", 4, 0, "now", null);

        //when
        when(mockedRestTemplate.getForObject(anyString(), any())).thenReturn(expectedUser);

        //then
        assertThatThrownBy(() -> userDataService.getUserData(expectedLogin)).isInstanceOf(DivideByZeroException.class);
    }

    @Test
    void willNotFindUser() {
        //given
        String expectedLogin = "tester";
        //when
        when(mockedRestTemplate.getForObject(anyString(), any())).thenThrow(HttpClientErrorException.class);

        //then
        assertThatThrownBy(() -> userDataService.getUserData(expectedLogin)).isInstanceOf(UserNotFoundException.class);
    }


    @Test
    void willFindNullException() {
        //given
        String expectedLogin = "tester";
        //when
        when(mockedRestTemplate.getForObject(anyString(), any())).thenReturn(null);

        //then
        assertThatThrownBy(() -> userDataService.getUserData(expectedLogin)).isInstanceOf(UserNotFoundException.class);
    }

    @Test
    void willTryToCheckIfUserWasInDatabase() {
        //given
        String expectedLogin = "tester";
        UserData expectedUser = new UserData(1L, expectedLogin, "Test", "www.avatar.url", 4, 2, "now", null);
        LoginStatistics expectedLoginData = new LoginStatistics(expectedLogin,2);

        //when
        when(mockedRestTemplate.getForObject(anyString(), any())).thenReturn(expectedUser);
        userDataService.getUserData(expectedLogin);

        // then
        verify(mockedLoginStatisticsRepository).findByLogin(expectedLogin);

    }

    @Test
    void willTryToSaveLoginStaticsWithIncrementedRequestCount() {
        //given
        String expectedLogin = "tester";
        UserData expectedUser = new UserData(1L, expectedLogin, "Test", "www.avatar.url", 4, 2, "now", null);
        Integer testRequestCount = 4;
        LoginStatistics expectedLoginStatistics = new LoginStatistics(expectedLogin,testRequestCount);

        //when
        when(mockedRestTemplate.getForObject(anyString(), any())).thenReturn(expectedUser);
        when(mockedLoginStatisticsRepository.findByLogin(anyString())).thenReturn(expectedLoginStatistics);
        userDataService.getUserData(expectedLogin);

        // then
        InOrder inOrder = inOrder(mockedLoginStatisticsRepository);

        inOrder.verify(mockedLoginStatisticsRepository).findByLogin(expectedLogin);
        inOrder.verify(mockedLoginStatisticsRepository).save(expectedLoginStatistics);
        assertThat(expectedLoginStatistics.getRequestCount()).isEqualTo(testRequestCount+1);
    }

    @Test
    void willTryToSaveLoginStaticsForNewLogin() {
        //given
        String expectedLogin = "tester";
        UserData expectedUser = new UserData(1L, expectedLogin, "Test", "www.avatar.url", 4, 2, "now", null);
        LoginStatistics expectedLoginStatistics = null;
        //when
        when(mockedRestTemplate.getForObject(anyString(), any())).thenReturn(expectedUser);
        when(mockedLoginStatisticsRepository.findByLogin(anyString())).thenReturn(expectedLoginStatistics);
        userDataService.getUserData(expectedLogin);

        // then
        InOrder inOrder = inOrder(mockedLoginStatisticsRepository);

        inOrder.verify(mockedLoginStatisticsRepository).findByLogin(expectedLogin);
        inOrder.verify(mockedLoginStatisticsRepository).save(notNull());

    }


}
