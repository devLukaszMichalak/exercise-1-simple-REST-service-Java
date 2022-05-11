package com.lukas.hiweb.userdata;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;


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

}
