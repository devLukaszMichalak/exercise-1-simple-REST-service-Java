package com.lukas.hiweb.userdata;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@RequiredArgsConstructor
@Data
@Entity
@Table
public class UserData {

    @Id
    @SequenceGenerator(
            name = "userdata_sequence",
            sequenceName = "userdata_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "userdata_sequence"
    )

    Long id;
    String login;
    String name;
    String avatar_url;
    Integer public_repos;
    Integer following;
    String created_at;
}
