package com.kacper.travelApp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kacper.travelApp.model.User;
import com.kacper.travelApp.model.dto.RegisterDto;
import com.kacper.travelApp.repository.UserRepository;
import com.kacper.travelApp.service.Service.SessionService;
import com.kacper.travelApp.service.SessionServiceImpl;
import com.kacper.travelApp.service.UserServiceImpl;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(controllers = AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServiceImpl userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private PasswordEncoder passwordEncoder; // Mock password encoder

    @MockBean
    private SessionServiceImpl sessionService;

    @Autowired
    private ObjectMapper objectMapper;

    private RegisterDto registerDto;

    @BeforeEach
    public void init() {
        registerDto = RegisterDto.builder()
                .login("login1")
                .password("password1")
                .email("email1@email.com")
                .build();
    }

    @Test
    public void AuthController_RegisterUser_RegisterSuccessfully() throws Exception {
        given(userRepository.existsByLogin(registerDto.getLogin())).willReturn(false);
        given(passwordEncoder.encode(ArgumentMatchers.anyString())).willReturn("encodedPassword");

        ResultActions response = mockMvc.perform(post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(registerDto)));

        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string("User registered!"));
    }
}
