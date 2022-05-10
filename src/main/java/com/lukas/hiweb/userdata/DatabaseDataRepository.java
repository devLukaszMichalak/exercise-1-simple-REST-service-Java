package com.lukas.hiweb.userdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatabaseDataRepository extends JpaRepository<DatabaseData, Long> {
    boolean existsByLogin(String login);
    DatabaseData findByLogin(String login);

}
