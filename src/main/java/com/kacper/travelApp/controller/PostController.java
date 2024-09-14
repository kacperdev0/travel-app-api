package com.kacper.travelApp.controller;

import com.kacper.travelApp.model.Plan;
import com.kacper.travelApp.repository.PlanRepository;
import com.kacper.travelApp.repository.UserRepository;
import io.netty.handler.codec.http.HttpResponse;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/posts")
public class PostController {
    private final PlanRepository planRepository;
    private final UserRepository userRepository;

    public PostController(PlanRepository planRepository, UserRepository userRepository) {
        this.planRepository = planRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/getAllPosts")
    public ResponseEntity<?> getPosts() {
        List<Plan> plans = planRepository.findAll();
        return new ResponseEntity<>(plans, HttpStatus.OK);
    }
}
