package com.lukas.hiweb.userdata;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Configuration
public class UserDataConfig {

    RestTemplate restTemplate = new RestTemplate();

    @Bean
    CommandLineRunner commandLineRunner(UserDataRepository repository){

        UserData user1 = restTemplate.getForObject("https://api.github.com/users/devLukaszMichalak", UserData.class);
        System.out.println(user1);

        return args -> {repository.saveAll(List.of(user1));};
    }


}
