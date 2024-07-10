package com.kacper.travelApp.repository;

import com.kacper.travelApp.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<Session, Integer> {
    public Session findSessionByJSSESSIONID(String JSSESSIONID);
}
