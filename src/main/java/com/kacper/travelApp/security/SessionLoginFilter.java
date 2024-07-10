package com.kacper.travelApp.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kacper.travelApp.model.LoginDto;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class SessionLoginFilter extends UsernamePasswordAuthenticationFilter {

}
