package com.endl.loopin.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final List<String> users = new ArrayList<>();

    public List<String> getAllUsers() {
        // Return the list of users
        return users;
    }

    public void createUser(String user) {
        // Add the new user to the list
        users.add(user);
    }
}
