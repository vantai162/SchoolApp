package org.example.schoolapp.repository;

import org.example.schoolapp.api.model.SchoolClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolClassRepository
        extends JpaRepository<SchoolClass, Integer> {
}
