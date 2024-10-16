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
    public void UserService_Save_ReturnsSavedUser() {
        User testUser = User.builder()
                .login("login1")
                .username("username1")
                .password("password1")
                .email("email1@email.com")
                .avatarUrl("link.link1").build();

        when(userRepository.save(Mockito.any(User.class))).thenReturn(testUser);

        User savedUser = userRepository.save(testUser);
        System.out.println(savedUser);
        Assertions.assertThat(savedUser).isNotNull();
    }

}
