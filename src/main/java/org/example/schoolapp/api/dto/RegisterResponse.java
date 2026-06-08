package org.example.schoolapp.api.dto;

import org.example.schoolapp.api.model.Role;

public record RegisterResponse(
        Integer id,
        String email,
        String name,
        Role role
) {
}