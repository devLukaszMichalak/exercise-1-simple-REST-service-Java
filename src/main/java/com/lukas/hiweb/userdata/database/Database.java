package com.lukas.hiweb.userdata.database;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "git_users")
public class Database {

    public Database(String login, Integer requestCount) {
        this.login = login;
        this.requestCount = requestCount;
    }

    @Id
    @SequenceGenerator(name = "databasedata_sequence", sequenceName = "databasedata_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "databasedata_sequence")

    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "login", nullable = false, columnDefinition = "TEXT")
    private String login;

    @Column(name = "request_count", nullable = false)
    private Integer requestCount;

}
