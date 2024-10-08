package com.kacper.travelApp.controller;

import com.kacper.travelApp.model.Session;
import com.kacper.travelApp.model.User;
import com.kacper.travelApp.repository.SessionRepository;
import com.kacper.travelApp.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RestController
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

    @PostMapping("avatarUrl/{newAvatarUrl}")
    public ResponseEntity<?> setUserAvatarUrl(HttpSession httpSession, @PathVariable String newAvatarUrl) {
        Optional<Session> session = sessionRepository.findSessionByJSSESSIONID(httpSession.getId());
        if (!session.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        System.out.println("Changing avatar URL");

        Optional<User> user = userRepository.findById(session.get().getUserId());
        User changedUser = user.get();
        changedUser.setAvatarUrl(newAvatarUrl);
        userRepository.save(changedUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("username/{username}")
    public ResponseEntity<?> setUsername(HttpSession httpSession, @PathVariable String username) {

        Optional<Session> session = sessionRepository.findSessionByJSSESSIONID(httpSession.getId());
        System.out.println(session.get());
        if (!session.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        System.out.println(username);

        Optional<User> user = userRepository.findById(session.get().getUserId());
        User changedUser = user.get();
        changedUser.setUsername(username);
        userRepository.save(changedUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
