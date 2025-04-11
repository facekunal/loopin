package com.endl.loopin.dto;

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

    // TODO: @kunal add field validation
    private String name;
    private String email;
    private String passwordHash;
}
