package com.lukas.hiweb.user.loginstatistics;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Table(name = "USERS_STATISTICS_DATA")
public class LoginStatistics {

    @Id
    @SequenceGenerator(name = "databasedata_sequence", sequenceName = "databasedata_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "databasedata_sequence")

    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "login", nullable = false, columnDefinition = "TEXT")
    private String login;

    @Getter
    @Setter
    @Column(name = "request_count", nullable = false)
    private Integer requestCount;

    public LoginStatistics(String login, Integer requestCount) {
        this.login = login;
        this.requestCount = requestCount;
    }
}
