package com.lukas.hiweb.userdata;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DatabaseDataService {

    private final DatabaseDataRepository databaseDataRepository;
    private final RestTemplate restTemplate;

    @Transactional//?
    public List<DatabaseData> getDatabaseData() {

        UserData user1 = restTemplate.getForObject("https://api.github.com/users/devLukaszMichalak", UserData.class);

        if(databaseDataRepository.existsByLogin(user1.getLogin())){

            DatabaseData entryToBeEdited = databaseDataRepository.findByLogin(user1.getLogin());
            int newCount = entryToBeEdited.getRequest_count()+1;
            entryToBeEdited.setRequest_count(newCount);
            databaseDataRepository.save(entryToBeEdited);

        }else{
            databaseDataRepository.save(new DatabaseData(1L,user1.getLogin(),1));
        }



        return databaseDataRepository.findAll();
    }

}

