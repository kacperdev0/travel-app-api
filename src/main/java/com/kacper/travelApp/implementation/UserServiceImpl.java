package com.kacper.travelApp.implementation;

import com.kacper.travelApp.model.User;
import com.kacper.travelApp.repositiory.UserRepository;
import com.kacper.travelApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepo;

    @Override
    public User registerUser(User user) throws Exception {
        return userRepo.save(user);
    }
}
