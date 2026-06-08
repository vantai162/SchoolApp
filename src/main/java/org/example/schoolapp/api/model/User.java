package org.example.schoolapp.api.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String email;
    private String password;
    private String name;
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "teacher")
    private List<SchoolClass> classes;


    @OneToMany(mappedBy = "student")
    private List<Enrollment> enrollments;


    public User(){

    }
    public int getId(){
        return id;
    }

    public Role getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
    public String getPassword(){
        return password;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setRole(Role role){
        this.role = role;
    }
}
