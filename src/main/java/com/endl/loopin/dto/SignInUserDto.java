package com.endl.loopin.dto;
import lombok.Data;

@Data
public class SignInUserDto {
    private String email; // Changed from username to email
    private String password;
}
