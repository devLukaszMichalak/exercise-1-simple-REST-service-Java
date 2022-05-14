package com.lukas.hiweb.user;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class UserData {

    private Long id;
    private String login;
    private String name;
    private String avatar_url;
    private Integer public_repos;
    private Integer followers;
    private String created_at;
    private Double calculations;

}
