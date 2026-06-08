package org.example.schoolapp.api.dto;

public record LoginRequest(
        String email,
        String password
) {
}
