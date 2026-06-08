package org.example.schoolapp.api.controller;

import org.example.schoolapp.api.dto.LoginRequest;
import org.example.schoolapp.api.dto.LoginResponse;
import org.example.schoolapp.api.dto.RegisterRequest;
import org.example.schoolapp.api.dto.RegisterResponse;
import org.example.schoolapp.api.service.AuthService;
import org.example.schoolapp.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {


    private final AuthService authService;

    public AuthController(AuthService authService) {

        this.authService = authService;
    }

    @PostMapping("/login")
    public LoginResponse login(
            @RequestBody LoginRequest request) {

        return authService.login(request);
    }

    @PostMapping("/register")
    public RegisterResponse register(
            @RequestBody RegisterRequest request) {

        return authService.register(request);
    }
}