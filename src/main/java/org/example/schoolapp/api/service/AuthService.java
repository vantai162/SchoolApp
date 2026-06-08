package org.example.schoolapp.api.service;

import org.example.schoolapp.api.dto.LoginRequest;
import org.example.schoolapp.api.dto.LoginResponse;
import org.example.schoolapp.api.dto.RegisterRequest;
import org.example.schoolapp.api.dto.RegisterResponse;
import org.example.schoolapp.api.model.User;
import org.example.schoolapp.api.repository.UserRepository;
import org.example.schoolapp.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService,
            AuthenticationManager authenticationManager) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public RegisterResponse register(
            RegisterRequest request) {

        if (userRepository.existsByEmail(request.email())) {
            throw new RuntimeException(
                    "Email already exists");
        }

        User user = new User();

        user.setEmail(request.email());

        user.setPassword(
                passwordEncoder.encode(
                        request.password()
                )
        );

        user.setName(request.name());

        user.setRole(request.role());

        User savedUser =
                userRepository.save(user);

        return new RegisterResponse(
                savedUser.getId(),
                savedUser.getEmail(),
                savedUser.getName(),
                savedUser.getRole()
        );
    }

    public LoginResponse login(LoginRequest request){

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );

        User user = userRepository
                .findByEmail(request.email());
        if (user == null)
            throw new RuntimeException("Email not found");

        String token = jwtService.generateToken(user);

        return new LoginResponse(token);
    }
}