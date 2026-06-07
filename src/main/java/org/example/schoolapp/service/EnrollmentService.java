package org.example.schoolapp.service;

import org.example.schoolapp.api.dto.StudentDto;
import org.example.schoolapp.api.model.Enrollment;
import org.example.schoolapp.api.model.User;
import org.example.schoolapp.repository.EnrollmentRepository;
import org.example.schoolapp.repository.SchoolClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentService {
    private EnrollmentRepository repository;
    @Autowired
    public EnrollmentService(EnrollmentRepository repos){
        repository = repos;
    }

    public List<StudentDto> getStudentByClassId(Integer id){
        List<Enrollment> query = repository.findBySchoolClassId(id);
        List<StudentDto> students = query.stream().map(
                e -> new StudentDto(
                        e.getStudent().getId(),
                        e.getStudent().getName()
                )
        ).toList();
        return students;
    }
}
