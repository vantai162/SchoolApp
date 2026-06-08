package org.example.schoolapp.api.repository;


import jakarta.persistence.criteria.CriteriaBuilder;
import org.example.schoolapp.api.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository
        extends JpaRepository<Enrollment, Integer> {
    List<Enrollment> findBySchoolClassId(Integer id);
    boolean existsByStudentIdAndSchoolClassId(Integer studentId, Integer classId);

}