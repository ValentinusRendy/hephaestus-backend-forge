package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

// import javax.validation.constraints.Email;
// import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
