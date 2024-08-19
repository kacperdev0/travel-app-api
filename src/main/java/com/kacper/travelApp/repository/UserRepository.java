package com.kacper.travelApp.repository;

import com.kacper.travelApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String login);
    User findById(long Id);
    boolean existsByLogin(String login);
}
