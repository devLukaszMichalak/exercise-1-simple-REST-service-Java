package com.lukas.hiweb.userdata;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "git_users")
public class DatabaseData {

        @Id
        @SequenceGenerator(
                name = "databasedata_sequence",
                sequenceName = "databasedata_sequence",
                allocationSize = 1
        )
        @GeneratedValue(
                strategy = GenerationType.SEQUENCE,
                generator = "databasedata_sequence"
        )

        private Long id;
        private String login;
        private Integer request_count;

}
