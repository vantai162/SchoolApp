package org.example.schoolapp.api.service;


import org.example.schoolapp.api.dto.CreateClassRequest;
import org.example.schoolapp.api.dto.SchoolClassDto;
import org.example.schoolapp.api.model.Role;
import org.example.schoolapp.api.model.SchoolClass;
import org.example.schoolapp.api.model.User;
import org.example.schoolapp.api.repository.SchoolClassRepository;
import org.example.schoolapp.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolClassService {
    private SchoolClassRepository schoolClassRepository;
    private UserRepository userRepository;
    @Autowired
    public SchoolClassService(SchoolClassRepository repos, UserRepository userRepository){
        schoolClassRepository = repos;
        this.userRepository = userRepository;
    }

    public List<SchoolClassDto> getAllClasses(){
        List<SchoolClass> query = schoolClassRepository.findAll();
        List<SchoolClassDto> result = query.stream().map(
                s -> new SchoolClassDto(
                    s.getId(), s.getClassName(),s.getTeacher().getName()
                )
        ).toList();
        return result;
    }

    public SchoolClassDto getClassById(Integer id){
        SchoolClass schoolClass = schoolClassRepository.findById(id).orElseThrow();
        return new SchoolClassDto(schoolClass.getId(),schoolClass.getClassName(),schoolClass.getTeacher().getName());
    }

    public SchoolClassDto createClass(CreateClassRequest request) {
        User teacher = userRepository.findById(request.teacherId()).orElseThrow();
        if (teacher.getRole() != Role.TEACHER)
            throw new RuntimeException(
                    "User is not a teacher");
        SchoolClass schoolClass = new SchoolClass();
        schoolClass.setClassName(request.className());
        schoolClass.setTeacher(teacher);
        schoolClassRepository.save(schoolClass);
        return new SchoolClassDto(schoolClass.getId(), schoolClass.getClassName() ,schoolClass.getTeacher().getName());
    }
}
