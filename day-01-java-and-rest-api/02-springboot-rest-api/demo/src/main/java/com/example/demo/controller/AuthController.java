package com.example.demo.controller;


// import com.aegira.loan.auth.dto.LoginRequest;
// import com.aegira.loan.auth.dto.LoginResponse;
// import com.aegira.loan.common.dto.ApiResponse;
import lombok.RequiredArgsConstructor;

import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.LoginResponse;
import com.example.demo.service.AuthService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/v2/auth")
@RequiredArgsConstructor

public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest request) {

        return ResponseEntity.ok(authService.login(request));
    }


    @GetMapping("/me")
    public ResponseEntity<LoginResponse> me() {
        return ResponseEntity.ok(authService.me());
    }
}
