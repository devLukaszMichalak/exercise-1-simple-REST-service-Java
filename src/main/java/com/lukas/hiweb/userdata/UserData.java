package com.lukas.hiweb.userdata;

import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
public class UserData {

    private Long id;
    private String login;
    private String name;
    private String avatar_url;
    private Integer public_repos;
    private Integer following;
    private String created_at;
    private Double calculations;

    public UserData(String login) {
        this.id = null;
        this.login = login;
        this.name = null;
        this.avatar_url = null;
        this.public_repos = null;
        this.following = null;
        this.created_at = null;
        this.calculations = null;
    }
}
