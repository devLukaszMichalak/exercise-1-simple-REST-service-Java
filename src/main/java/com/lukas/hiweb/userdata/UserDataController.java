package com.lukas.hiweb.userdata;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/devLukaszMichalak")
@RequiredArgsConstructor
public class UserDataController {
    UserData userData;
    private final UserDataService userDataService;

    @GetMapping
    public List<UserData> getUserData(){
        return userDataService.getUserData();
    }

}
