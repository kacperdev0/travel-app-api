package com.kacper.travelApp.service;

import com.kacper.travelApp.model.Post;
import com.kacper.travelApp.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getPostsWithPlans() {
        return postRepository.findAll();
    }
}
