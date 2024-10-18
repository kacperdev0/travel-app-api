package com.kacper.travelApp.service;

import com.kacper.travelApp.model.User;
import com.kacper.travelApp.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional // Automatically rolls back transactions after each test method
public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void saveUser_WhenCalled_UserShouldBePresentInDatabase() {
        User testUser = User.builder()
                .login("login1")
                .username("username1")
                .password("password1")
                .email("email1@email.com")
                .avatarUrl("link.link1")
                .build();

        userRepository.save(testUser);

        User savedUser = userRepository.findByLogin(testUser.getLogin());

        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getLogin()).isEqualTo(testUser.getLogin());
        Assertions.assertThat(savedUser.getEmail()).isEqualTo(testUser.getEmail());

        System.out.println("Test passed");
    }
}
