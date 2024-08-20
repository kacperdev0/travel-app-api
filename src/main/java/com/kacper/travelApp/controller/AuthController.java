package com.kacper.travelApp.controller;

import com.kacper.travelApp.model.*;
import com.kacper.travelApp.model.dto.LoginDto;
import com.kacper.travelApp.model.dto.RegisterDto;
import com.kacper.travelApp.repository.UserRepository;
import com.kacper.travelApp.service.SessionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RestController
 @RequestMapping("api/auth")
public class AuthController {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private SessionService sessionService;




    @Autowired
    public AuthController(UserRepository userRepository,
                          PasswordEncoder passwordEncoder,
                          SessionService sessionService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.sessionService = sessionService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        if (userRepository.existsByLogin(registerDto.getLogin())) {
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        user.setLogin(registerDto.getLogin());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setEmail(registerDto.getEmail());

        userRepository.save(user);

        return new ResponseEntity<>("User registered!", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto, HttpSession httpSession) {
        User user = userRepository.findByLogin(loginDto.getLogin());
        if (passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            sessionService.saveSession(httpSession, user);
            return new ResponseEntity<>("Logged successfully!", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Incorrect login data!", HttpStatus.UNAUTHORIZED);
    }
}
