package com.kacper.travelApp.controller;

import com.kacper.travelApp.model.Session;
import com.kacper.travelApp.model.User;
import com.kacper.travelApp.repository.SessionRepository;
import com.kacper.travelApp.repository.UserRepository;
import com.kacper.travelApp.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000/")
@RequestMapping("api/protected")
public class UserController {

    public UserRepository userRepository;
    public SessionRepository sessionRepository;

    public UserController(UserRepository userRepository, SessionRepository sessionRepository) {
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
    }

    @GetMapping("userDetails")
    public ResponseEntity<?> getUserDetails(HttpSession session) {
        Optional<Session> s = sessionRepository.findSessionByJSSESSIONID(session.getId());
        Optional<User> user = userRepository.findById(s.get().getUserId());
        return new ResponseEntity<>(user.get(), HttpStatus.OK);
    }
}
