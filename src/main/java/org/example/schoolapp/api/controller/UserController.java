package org.example.schoolapp.api.controller;

import org.example.schoolapp.api.model.Role;
import org.example.schoolapp.api.model.User;
import org.example.schoolapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/user")
    public User getUser(@RequestParam Integer id){
        return userService.getUserById(id);
    }

    @GetMapping("/users")
    public List<User> getAllUsers(@RequestParam(required = false) Role role){
        if (role == null)
            return userService.getAllUsers();
        else
            return userService.getAllUsersByRole(role);
    }
}
