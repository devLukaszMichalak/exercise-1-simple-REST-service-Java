package com.lukas.hiweb.userdata;

import com.lukas.hiweb.userdata.database.Database;
import com.lukas.hiweb.userdata.database.DatabaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class UserDataService {

    private final DatabaseRepository databaseRepository;
    private final RestTemplate restTemplate;

    public UserData getUserData(String login) {

        UserData userData;
        try {
            userData = restTemplate.getForObject(("https://api.github.com/users/" + login), UserData.class);
        } catch (HttpClientErrorException e) {
            userData = new UserData(login);
            //throw e;
        }


        if (userData.getId() != null) {
            try {
                userData.setCalculations(performCalculation(userData));
            } catch (ArithmeticException e) {
                userData.setCalculations(null);
                //throw e;
            }
            incrementRequetCount(userData.getLogin());
        } else {
            incrementRequetCount("NOT FOUND");//counting calls for non existing users in database
        }

        return userData;
    }

    private double performCalculation(UserData userData) throws ArithmeticException {
        if (userData.getFollowing() == 0) {
            throw new ArithmeticException("Calculations cannot be performed - follower number is 0");
        }
        return (6.0 / userData.getFollowing()) * (2 + userData.getPublic_repos());
    }

    private void incrementRequetCount(String login) {
        Database entryToBeEdited = databaseRepository.findByLogin(login);
        if (entryToBeEdited != null) {
            int newCount = entryToBeEdited.getRequestCount() + 1;
            entryToBeEdited.setRequestCount(newCount);
            databaseRepository.save(entryToBeEdited);

        } else {
            databaseRepository.save(new Database(login, 1));
        }
    }

}

