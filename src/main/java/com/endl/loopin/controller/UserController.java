package com.endl.loopin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.endl.loopin.service.UserService;

import java.util.List;

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
    public ResponseEntity<String> createUser(@RequestBody String user) {
        // Call service to create a new user
        userService.createUser(user);
        return ResponseEntity.ok("User created successfully");
    }
}
