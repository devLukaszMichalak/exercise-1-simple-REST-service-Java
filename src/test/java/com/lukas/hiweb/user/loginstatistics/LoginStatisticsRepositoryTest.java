package com.lukas.hiweb.user.loginstatistics;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class LoginStatisticsRepositoryTest {

    @Autowired
    private LoginStatisticsRepository loginStatisticsRepository;

    @Test
    void shouldCheckIfFoundsCorrectUser() {
        //given
        String testUser = "Tester";
        Integer testUserRequestCount = 2;
        LoginStatistics testUserLoginStatistics = new LoginStatistics(testUser, testUserRequestCount);
        loginStatisticsRepository.save(testUserLoginStatistics);

        //when
        LoginStatistics foundUser = loginStatisticsRepository.findByLogin(testUser);

        //then
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(foundUser.getLogin()).isEqualTo(testUser);
        softly.assertThat(foundUser.getRequestCount()).isEqualTo(testUserRequestCount);
        softly.assertAll();

    }
}