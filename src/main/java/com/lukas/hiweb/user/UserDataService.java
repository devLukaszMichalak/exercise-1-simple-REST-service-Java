package com.lukas.hiweb.user;

import com.lukas.hiweb.user.loginstatistics.LoginStatistics;
import com.lukas.hiweb.user.loginstatistics.LoginStatisticsRepository;
import com.lukas.hiweb.user.exceptions.DivideByZeroException;
import com.lukas.hiweb.user.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class UserDataService {

    private final LoginStatisticsRepository loginStatisticsRepository;
    private final RestTemplate restTemplate;
    @Value("${api.github.users.url}")
    private String apiGithubUsersUrl;

    public UserData getUserData(String login) {

        incrementRequetCount(login);

        try {
            UserData userData = restTemplate.getForObject((apiGithubUsersUrl + login), UserData.class);
            double calculationValue = performCalculation(userData);
            userData.setCalculations(calculationValue);
            return userData;
        } catch (HttpClientErrorException|NullPointerException e) {
            throw new UserNotFoundException(login);
        } catch (DivideByZeroException e){
            throw new DivideByZeroException();
        }

    }

    private double performCalculation(UserData userData) throws DivideByZeroException {
        if (userData.getFollowers() == 0) {
            throw new DivideByZeroException();
        }
        return (6.0 / userData.getFollowers()) * (2 + userData.getPublic_repos());
    }

    private void incrementRequetCount(String login) {
        LoginStatistics entryToBeEdited = loginStatisticsRepository.findByLogin(login);
        if (entryToBeEdited != null) {
            int newCount = entryToBeEdited.getRequestCount() + 1;
            entryToBeEdited.setRequestCount(newCount);
            loginStatisticsRepository.save(entryToBeEdited);

        } else {
            loginStatisticsRepository.save(new LoginStatistics(login, 1));
        }
    }

}

