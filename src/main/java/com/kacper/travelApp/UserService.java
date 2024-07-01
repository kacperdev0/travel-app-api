package com.kacper.travelApp;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    List<User> getAllUsers();
}
