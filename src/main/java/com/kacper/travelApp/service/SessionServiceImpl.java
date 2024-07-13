package com.kacper.travelApp.service;

import com.kacper.travelApp.model.Session;
import com.kacper.travelApp.model.User;
import com.kacper.travelApp.repository.SessionRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SessionServiceImpl implements SessionService {

    private SessionRepository sessionRepository;

    @Autowired
    public SessionServiceImpl(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Override
    public Session saveSession(HttpSession httpSession, User user) {
        String sessionID = httpSession.getId();
        Optional<Session> sessionOptional = sessionRepository.findSessionByJSSESSIONID(sessionID);

        if (sessionOptional.isPresent()) {
            Session session = sessionOptional.get();
            session.updateCreatedAt();
            return sessionRepository.save(session);
        }

        Session session = new Session();
        session.setJSSESSIONID(httpSession.getId());
        session.setUserId(user.getId());
        session.updateCreatedAt();
        return sessionRepository.save(session);
    }

    @Override
    public boolean isSessionActive(String sessionId) {
        if (sessionRepository.existsByJSSESSIONID(sessionId)) {
            return true;
        }
        return false;
    }
}
