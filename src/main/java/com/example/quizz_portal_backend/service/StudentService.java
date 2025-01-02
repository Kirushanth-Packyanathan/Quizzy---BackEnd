package com.example.quizz_portal_backend.service;

import com.example.quizz_portal_backend.entity.Student;
import com.example.quizz_portal_backend.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    // Method to find a student by email
    public Student findByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    // Method to save a student
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    // Method to check if a student with a given email exists
    public boolean existsByEmail(String email) {
        return studentRepository.existsByEmail(email);
    }
}
