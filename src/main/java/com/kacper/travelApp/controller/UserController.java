package com.kacper.travelApp.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000/")
@RequestMapping("api/protected")
public class UserController {

    @GetMapping("userDetails")
    public ResponseEntity<?> getUserDetails(HttpSession session) {
        return new ResponseEntity<String>("Get user executed", HttpStatus.OK);
    }
}
