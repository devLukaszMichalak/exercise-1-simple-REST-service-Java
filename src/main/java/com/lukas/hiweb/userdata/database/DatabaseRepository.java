package com.lukas.hiweb.userdata.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatabaseRepository extends JpaRepository<Database, Long> {
    boolean existsByLogin(String login);
    Database findByLogin(String login);

}
