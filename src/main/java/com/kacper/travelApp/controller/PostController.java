package com.kacper.travelApp.controller;

import com.kacper.travelApp.model.Plan;
import com.kacper.travelApp.model.Post;
import com.kacper.travelApp.repository.PlanRepository;
import com.kacper.travelApp.repository.PostRepository;
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
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostController(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/getAllPosts")
    public ResponseEntity<?> getPosts() {
        List<Post> posts = postRepository.findAll();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
}
