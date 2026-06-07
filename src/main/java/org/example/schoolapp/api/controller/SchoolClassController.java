package org.example.schoolapp.api.controller;

import org.example.schoolapp.api.dto.CreateClassRequest;
import org.example.schoolapp.api.dto.SchoolClassDto;
import org.example.schoolapp.api.dto.StudentDto;
import org.example.schoolapp.api.model.SchoolClass;
import org.example.schoolapp.api.model.User;
import org.example.schoolapp.service.EnrollmentService;
import org.example.schoolapp.service.SchoolClassService;
import org.example.schoolapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class SchoolClassController {
    private SchoolClassService schoolClassService;
    private EnrollmentService enrollmentService;

    @Autowired
    public SchoolClassController(SchoolClassService schoolClassService, EnrollmentService enrollmentService){
        this.schoolClassService = schoolClassService;
        this.enrollmentService = enrollmentService;
    }

    @GetMapping("/classes")
    public List<SchoolClassDto> getAllClasses(){
        return schoolClassService.getAllClasses();
    }

    @GetMapping("/classes/{id}")
    public SchoolClassDto getClassById(
            @PathVariable Integer id) {
        return schoolClassService.getClassById(id);
    }

    @GetMapping("/classes/{classId}/students")
    public List<StudentDto> getStudentsByClassId(@PathVariable Integer classId){
        return enrollmentService.getStudentByClassId(classId);
    }

    @PostMapping("/classes")
    public SchoolClassDto createClass(
            @RequestBody CreateClassRequest request) {

        return schoolClassService.createClass(request);
    }
}
