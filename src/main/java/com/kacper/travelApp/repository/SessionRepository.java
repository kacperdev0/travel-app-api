package com.kacper.travelApp.repository;

import com.kacper.travelApp.model.Plan;
import com.kacper.travelApp.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session, Integer> {
    public Optional<Session> findSessionByJSSESSIONID(String JSSESSIONID);
    public Optional<Session> findSessionByUserId(Long userId);
}
