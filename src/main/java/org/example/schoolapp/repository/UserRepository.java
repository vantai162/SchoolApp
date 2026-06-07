package org.example.schoolapp.repository;

import org.example.schoolapp.api.model.Role;
import org.example.schoolapp.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByRole(Role role);

}
