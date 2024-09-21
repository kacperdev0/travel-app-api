package com.kacper.travelApp.service.Service;

import com.kacper.travelApp.model.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    List<User> getAllUsers();
}
