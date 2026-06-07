package org.example.schoolapp.service;

import org.example.schoolapp.api.model.Role;
import org.example.schoolapp.api.model.User;
import org.example.schoolapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.example.schoolapp.api.model.Role.STUDENT;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Integer id){
        return userRepository.getReferenceById(id);
    }

    public List<User> getAllUsersByRole(Role role) {
        return userRepository.findByRole(role);
    }
}
