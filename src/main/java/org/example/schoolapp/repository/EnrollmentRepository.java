package org.example.schoolapp.repository;


import org.example.schoolapp.api.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository
        extends JpaRepository<Enrollment, Integer> {
    List<Enrollment> findBySchoolClassId(Integer id);
}