package org.example.schoolapp.api.service;

import org.example.schoolapp.api.dto.EnrollStudentRequest;
import org.example.schoolapp.api.dto.StudentDto;
import org.example.schoolapp.api.model.Enrollment;
import org.example.schoolapp.api.model.Role;
import org.example.schoolapp.api.model.SchoolClass;
import org.example.schoolapp.api.model.User;
import org.example.schoolapp.api.repository.EnrollmentRepository;
import org.example.schoolapp.api.repository.SchoolClassRepository;
import org.example.schoolapp.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EnrollmentService {
    private EnrollmentRepository repository;
    private UserRepository userRepository;
    private SchoolClassRepository schoolClassRepository;

    @Autowired
    public EnrollmentService(EnrollmentRepository repos, UserRepository userRepository, SchoolClassRepository schoolClassRepository)
    {
        repository = repos;
        this.userRepository = userRepository;
        this.schoolClassRepository = schoolClassRepository;
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

    public void enrollStudent(EnrollStudentRequest request){
        User student = userRepository.findById(request.studentId()).orElseThrow();
        if (student == null)
            throw new RuntimeException("Student is not found");
        if (student.getRole() != Role.STUDENT)
            throw new RuntimeException("Id given is not a student");
        SchoolClass schoolClass = schoolClassRepository.findById(request.classId()).orElseThrow();
        if (schoolClass == null)
            throw new RuntimeException("Class is not found");
        boolean alreadyEnrolled =
                repository
                        .existsByStudentIdAndSchoolClassId(
                                request.studentId(),
                                request.classId());
        if (alreadyEnrolled) {
            throw new RuntimeException(
                    "Student already enrolled");
        }

        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setSchoolClass(schoolClass);

        enrollment.setEnrollmentDate(LocalDate.now());

        repository.save(enrollment);
    }
}
