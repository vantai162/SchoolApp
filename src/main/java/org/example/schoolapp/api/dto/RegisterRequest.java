package org.example.schoolapp.api.dto;

import org.example.schoolapp.api.model.Role;

public record RegisterRequest(
        String email,
        String password,
        String name,
        Role role
) {
}