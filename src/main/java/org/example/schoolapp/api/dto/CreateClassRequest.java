package org.example.schoolapp.api.dto;

public record CreateClassRequest(
        String className,
        Integer teacherId
) {
}