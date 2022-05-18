package com.lukas.hiweb.user;

import com.lukas.hiweb.user.exceptions.UserNotFoundException;
import com.lukas.hiweb.user.loginstatistics.LoginStatisticsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class UserDataServiceTest {

    @Mock
    private LoginStatisticsRepository loginStatisticsRepository;

    private RestTemplate restTemplate;
    private UserDataService userDataService;

    @BeforeEach
    void setUp() {
        userDataService = new UserDataService(loginStatisticsRepository, restTemplate);
    }


    @Test
    void willThrowUserNotFoundException() {
        //given
        String notExistingAccount = "tester";

        //when then
        assertThatThrownBy(() -> userDataService.getUserData(notExistingAccount)).isInstanceOf(UserNotFoundException.class);
    }


}
