package com.lukas.hiweb.userdata;

import com.lukas.hiweb.userdata.database.DatabaseData;
import com.lukas.hiweb.userdata.database.DatabaseDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDataService {

    private final DatabaseDataRepository databaseDataRepository;
    private final RestTemplate restTemplate;

    //@Transactional//?
    public List<UserData> getUserData(String newUserLogin) {

        UserData user1 = restTemplate.getForObject(("https://api.github.com/users/"+newUserLogin), UserData.class);
        user1.setCalculations((6.0/user1.getFollowing())*(2+user1.getPublic_repos()));

        if(databaseDataRepository.existsByLogin(user1.getLogin())){

            DatabaseData entryToBeEdited = databaseDataRepository.findByLogin(user1.getLogin());
            int newCount = entryToBeEdited.getRequest_count()+1;
            entryToBeEdited.setRequest_count(newCount);
            databaseDataRepository.save(entryToBeEdited);

        }else{
            databaseDataRepository.save(new DatabaseData(user1.getId(), user1.getLogin(),1));
        }

        return List.of(user1);
    }

}

