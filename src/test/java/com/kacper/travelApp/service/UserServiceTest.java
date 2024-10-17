package com.kacper.travelApp.service;

import com.kacper.travelApp.model.User;
import com.kacper.travelApp.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void saveUser_WhenCalled_ReturnsSavedUser() {
        User testUser = User.builder()
                .login("login1")
                .username("username1")
                .password("password1")
                .email("email1@email.com")
                .avatarUrl("link.link1").build();

        userRepository.save(testUser);

        User savedUser = userRepository.findByLogin(testUser.getLogin());
        Assertions.assertThat(savedUser).isNotNull();
    }

}
