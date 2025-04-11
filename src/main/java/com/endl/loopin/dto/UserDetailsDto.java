package com.endl.loopin.dto;

import lombok.Data;

@Data
public class UserDetailsDto {
    private Long id;
    private String name;
    private String email;
    private boolean isEmailVerified;
    private boolean isTwoFactorEnabled;

    public UserDetailsDto(Long id, String name, String email, boolean isEmailVerified, boolean isTwoFactorEnabled) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.isEmailVerified = isEmailVerified;
        this.isTwoFactorEnabled = isTwoFactorEnabled;
    }
}