package com.kacper.travelApp.controller;

import com.kacper.travelApp.model.*;
import com.kacper.travelApp.repository.RoleRepository;
import com.kacper.travelApp.repository.UserRepository;
import com.kacper.travelApp.service.SessionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@CrossOrigin("http://localhost:3000/")
@RestController
 @RequestMapping("api/auth")
public class AuthController {
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private SessionService sessionService;




    @Autowired
    public AuthController(AuthenticationManager authenticationManager,
                          UserRepository userRepository,
                          RoleRepository roleRepository,
                          PasswordEncoder passwordEncoder,
                          SessionService sessionService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
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

        Role roles = roleRepository.findByName("USER").get();
        user.setRoles((Collections.singletonList(roles)));
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
        return new ResponseEntity<>("Incorrect login data!", HttpStatus.OK);
    }
}
