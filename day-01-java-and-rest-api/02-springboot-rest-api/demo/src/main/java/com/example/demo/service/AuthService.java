package com.example.demo.service;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.LoginResponse;
import com.example.demo.exception.BadRequestException;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.security.AuthUtil;

import jakarta.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtTokenProvider jwtTokenProvider;
    private final AuthUtil authUtil;

    private final Map<String, User> userRepository = new HashMap<>();

    @PostConstruct
    public void init() {
        User user1 = new User();
        user1.setId(UUID.randomUUID());
        user1.setUsername("admin");
        user1.setPassword("password123"); 
        user1.setRole(Role.ADMIN);

        User user2 = new User();
        user2.setId(UUID.randomUUID());
        user2.setUsername("staff");
        user2.setPassword("password123");
        user2.setRole(Role.STAFF);

        User user3 = new User();
        user3.setId(UUID.randomUUID());
        user3.setUsername("approver");
        user3.setPassword("password123");
        user3.setRole(Role.APPROVER);

        userRepository.put(user1.getUsername(), user1);
        userRepository.put(user2.getUsername(), user2);
        userRepository.put(user3.getUsername(), user3);
    }

    public Map<String, User> getUserRepository() {
        return userRepository;
    }

    public LoginResponse login(LoginRequest request) {
        User user = userRepository.get(request.getUsername());

        if (user == null) {
            throw new BadRequestException("Invalid username or password");
        }

        if (!request.getPassword().equals(user.getPassword())) {
            throw new BadRequestException("Invalid username or password");
        }

        String token = jwtTokenProvider.generate(user);

        return new LoginResponse(user.getUsername(), user.getRole(), token);
    }

    public LoginResponse me() {
        String username = authUtil.currentUsername();

        User user = userRepository.get(username);

        if (user == null) {
            throw new BadRequestException("User not found");
        }

        return new LoginResponse(user.getUsername(), user.getRole(), null);
    }
}
