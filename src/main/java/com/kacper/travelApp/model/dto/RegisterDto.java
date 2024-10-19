package com.kacper.travelApp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class RegisterDto {
    private String login;
    private String password;
    private String email;

}
