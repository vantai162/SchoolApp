package org.example.schoolapp.api.dto;

public record EnrollStudentRequest(
        Integer studentId,
        Integer classId
) {
}
