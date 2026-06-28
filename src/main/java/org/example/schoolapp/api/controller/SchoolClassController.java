package org.example.schoolapp.api.controller;

import jakarta.mail.MessagingException;
import org.example.schoolapp.api.dto.CreateClassRequest;
import org.example.schoolapp.api.dto.EnrollStudentRequest;
import org.example.schoolapp.api.dto.SchoolClassDto;
import org.example.schoolapp.api.dto.StudentDto;
import org.example.schoolapp.api.service.EmailService;
import org.example.schoolapp.api.service.EnrollmentService;
import org.example.schoolapp.api.service.SchoolClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
public class SchoolClassController {
    private SchoolClassService schoolClassService;
    private EnrollmentService enrollmentService;
    private EmailService emailService;

    @Autowired
    public SchoolClassController(SchoolClassService schoolClassService, EnrollmentService enrollmentService,
                                 EmailService emailService){
        this.schoolClassService = schoolClassService;
        this.enrollmentService = enrollmentService;
        this.emailService = emailService;
    }

    @GetMapping("/classes")
    public List<SchoolClassDto> getAllClasses() throws MessagingException, UnsupportedEncodingException {
        String htmlBody = "<html><body><h2 style='color:blue'>Hello world</h2><p>Aloooo</p></body></html>";
        emailService.sendHtmlEmail("hoangvanteo162@gmail.com", "Hello world", htmlBody);
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

    @PostMapping("/classes/enroll")
    public ResponseEntity<Void> enrollClass(@RequestBody EnrollStudentRequest request){
        enrollmentService.enrollStudent(request);
        return ResponseEntity.ok().build();
    }


}
