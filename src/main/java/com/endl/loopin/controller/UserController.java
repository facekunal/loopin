package com.endl.loopin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.endl.loopin.service.UserService;
import com.endl.loopin.dto.UserDto;

import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<String>> getAllUsers() {
        // Call service to get all users
        List<String> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserDto userDto) {
        // Map UserDto to User entity and call service to create a new user
        userService.createUser(userDto);
        return ResponseEntity.ok("User created successfully");
    }
}
