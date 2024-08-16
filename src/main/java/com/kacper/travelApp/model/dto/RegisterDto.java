package com.kacper.travelApp.model.dto;

import lombok.Data;

@Data
public class  RegisterDto {
    private String login;
    private String password;
    private String email;
}
