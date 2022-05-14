package com.lukas.hiweb.user.loginstatistics;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginStatisticsRepository extends JpaRepository<LoginStatistics, Long> {
    LoginStatistics findByLogin(String login);

}
