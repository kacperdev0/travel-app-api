package com.kacper.travelApp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterDto {
    private String login;
    private String password;
    private String email;

}
