package com.kacper.travelApp.service;

import com.kacper.travelApp.repository.UserRepository;
import com.kacper.travelApp.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepo;

    public UserServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public Optional<User> findByLogin(String login) {
        return Optional.ofNullable(userRepo.findByLogin(login));
    }

    @Override
    public User saveUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }


}
