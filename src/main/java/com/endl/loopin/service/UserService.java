package com.endl.loopin.service;

import org.springframework.stereotype.Service;
import com.endl.loopin.dto.SignUpUserDto;
import com.endl.loopin.dto.UserDetailsDto;
import com.endl.loopin.entity.User;
import com.endl.loopin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder = new PasswordEncoder() {
        @Override
        public String encode(CharSequence rawPassword) {
            // Implement your encoding logic here
            return rawPassword.toString(); // Placeholder, replace with actual encoding
        }

        @Override
        public boolean matches(CharSequence rawPassword, String encodedPassword) {
            // Implement your matching logic here
            return rawPassword.toString().equals(encodedPassword); // Placeholder, replace with actual matching
        }
    };

    // TODO: @kunal use mapper
    // @Autowired
    // private UserMapper userMapper;

    public List<UserDetailsDto> getAllUsers() {
        // Fetch all users from the database and return their names
        return userRepository.findAll().stream()
                .map(user -> new UserDetailsDto(user.getId(), user.getName(), user.getEmail(), user.isEmailVerified(), user.isTwoFactorEnabled()))
                .toList();
    }

    public UserDetailsDto getUserById(Long id) {
        // Fetch user by ID from the database
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return new UserDetailsDto(user.getId(), user.getName(), user.getEmail(), user.isEmailVerified(), user.isTwoFactorEnabled());
    }

    public void createUser(SignUpUserDto userDto) {
        // Map UserDto to User entity
        // User user = UserMapper.toEntity(userDto);

        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPasswordHash(userDto.getPasswordHash());
        
        // by default false when a new user is created
        user.setEmailVerified(false);
        user.setTwoFactorEnabled(false);

        // Save user to the database
        userRepository.save(user);
    }

    public boolean authenticateUser(String email, String rawPassword) {
        return userRepository.findByEmail(email) // Correct usage of findBy
                .map(user -> passwordEncoder.matches(rawPassword, user.getPasswordHash()))
                .orElse(false);
    }
}
