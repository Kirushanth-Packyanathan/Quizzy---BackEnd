package com.example.quizz_portal_backend.repository;

import com.example.quizz_portal_backend.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

    // Method to find a student by email
    Student findByEmail(String email);

    // Method to check if a student with the given email exists
    boolean existsByEmail(String email);
}
