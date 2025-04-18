package com.endl.loopin.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Data;

/**
 * DTO for user sign-up information.
 * This class is used to encapsulate the data required for a user to sign up.
 * 
 * Fields:
 *   name: The name of the user signing up.
 *   email: The email address of the user signing up.
 *   passwordHash: The hashed password of the user signing up.
 * 
 * Note: Only include fields that are necessary for the sign-up process.
 */
@Data
public class SignUpUserDto {
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2, max = 20, message = "Name must be between 2 and 20 characters")
    private String name;

    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Password cannot be blank")
    private String password;
}
