package org.example.schoolapp.api.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "enrollments")
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private User student;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private SchoolClass schoolClass;

    private LocalDate enrollmentDate;

    private Double grade;

    public Enrollment() {
    }

    public User getStudent() {
        return student;
    }
}
