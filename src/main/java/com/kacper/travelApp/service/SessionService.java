package com.kacper.travelApp.service;

import com.kacper.travelApp.model.Session;
import com.kacper.travelApp.model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;

public interface SessionService {
    Session saveSession(HttpSession httpSession, User user);
    boolean isSessionActive(String sessionId);
}
