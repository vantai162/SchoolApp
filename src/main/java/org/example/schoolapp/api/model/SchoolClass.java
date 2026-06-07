package org.example.schoolapp.api.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "classes")
public class SchoolClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "class_name")
    private String className;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private User teacher;

    @OneToMany(mappedBy = "schoolClass")
    private List<Enrollment> enrollments;

    public Integer getId() {
        return id;
    }
    public String getClassName(){
        return className;
    }
    public User getTeacher(){
        return teacher;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

    public void setClassName(String s) {
        className = s;
    }
}
