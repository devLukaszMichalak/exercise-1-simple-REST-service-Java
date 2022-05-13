package com.lukas.hiweb.userdata;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/users")
@RequiredArgsConstructor
public class UserDataController {

    private final UserDataService userDataService;

    @GetMapping(path = "{newUserLogin}")
    public UserData userData(@PathVariable("newUserLogin") String newUserLogin) {
        return userDataService.getUserData(newUserLogin);
    }

}
