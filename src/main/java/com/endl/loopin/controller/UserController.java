package com.endl.loopin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.endl.loopin.service.UserService;

import jakarta.validation.Valid;

import com.endl.loopin.dto.SignUpUserDto;
import com.endl.loopin.dto.UserDetailsDto;
import com.endl.loopin.dto.SignInUserDto;

import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * For fetching all users.
     * 
     * @return A ResponseEntity containing a list of UserDetailsDto objects.
     * 
     *         Endpoint: /user
     *         HTTP Method: GET
     *         Response: 200 OK with a list of UserDetailsDto objects.
     */
    @GetMapping
    public ResponseEntity<List<UserDetailsDto>> getAllUsers() {
        List<UserDetailsDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    /**
     * For fetching a specific user by ID.
     * 
     * @param id The ID of the user to fetch.
     * @return A ResponseEntity containing the UserDetailsDto object for the specified
     *         user.
     * 
     *         Endpoint: /user/{id}
     *         HTTP Method: GET
     *         Response: 200 OK with the UserDetailsDto object for the specified user.
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserDetailsDto> getUserById(@PathVariable Long id) {
        UserDetailsDto user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }


    /**
     * For user signup.
     * 
     * @param userDto The DTO containing user signup details.
     * @return A ResponseEntity containing a success message if the user is created
     *         successfully.
     * 
     *         Endpoint: /user/signup
     *         HTTP Method: POST
     *         Request Body: SignUpUserDto (JSON)
     *         Response: 200 OK with a success message.
     */
    @PostMapping("/signup")
    public ResponseEntity<String> createUser(@Valid @RequestBody SignUpUserDto userDto) {
        try {
            userService.signup(userDto);
            return ResponseEntity.ok("User created successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An unexpected error occurred during signup");
        }
    }

    /**
     * For user signin.
     * 
     * @param signInUserDto The DTO containing user signin details.
     * @return A ResponseEntity containing a success message if the user is authenticated
     *         successfully.
     * 
     *         Endpoint: /user/signin
     *         HTTP Method: POST
     *         Request Body: SignInUserDto (JSON)
     *         Response: 200 OK with a success message or 401 Unauthorized if authentication fails.
     */
    @PostMapping("/signin")
    public ResponseEntity<String> signInUser(@Valid @RequestBody SignInUserDto signInUserDto) {
        try {
            boolean isAuthenticated = userService.signin(signInUserDto);
            if (isAuthenticated) {
                return ResponseEntity.ok("User signed in successfully");
            } else {
                return ResponseEntity.status(401).body("Invalid credentials");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An unexpected error occurred during signin");
        }
    }
}
