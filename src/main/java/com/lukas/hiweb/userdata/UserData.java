package com.lukas.hiweb.userdata;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;


@RequiredArgsConstructor
@Data
public class UserData {

    private Long id;
    private String login;
    private String name;
    private String avatar_url;
    private Integer public_repos;
    private Integer following;
    private String created_at;
}
