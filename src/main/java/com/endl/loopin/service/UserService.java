package com.endl.loopin.service;

import org.springframework.stereotype.Service;
import com.endl.loopin.dto.UserDto;
import com.endl.loopin.entity.User;
import com.endl.loopin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<String> getAllUsers() {
        // Fetch all users from the database and return their names
        return userRepository.findAll().stream()
                .map(User::getName)
                .toList();
    }

    public void createUser(UserDto userDto) {
        // Map UserDto to User entity
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPasswordHash(userDto.getPasswordHash());
        user.setEmailVerified(false);
        user.setTwoFactorEnabled(false);

        // Save user to the database
        userRepository.save(user);
    }
}
