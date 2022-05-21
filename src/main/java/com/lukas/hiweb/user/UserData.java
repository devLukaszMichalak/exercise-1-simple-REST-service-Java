package com.lukas.hiweb.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserData {

    private Long id;
    private String login;
    private String name;
    @JsonProperty("avatar_url")
    private String avatarUrl;
    @JsonProperty("public_repos")
    private Integer publicRepos;
    private Integer followers;
    @JsonProperty("created_at")
    private String createdAt;
    private Double calculations;

}
