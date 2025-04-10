package com.endl.loopin.dto;

import lombok.Data;

@Data // Lombok annotation to generate getters, setters, toString, equals, and hashCode
public class UserDto {
    private String name;
    private String email;
    private String passwordHash;
}
