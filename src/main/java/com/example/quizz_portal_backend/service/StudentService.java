package com.example.quizz_portal_backend.service;


import com.example.quizz_portal_backend.entity.Student;
import com.example.quizz_portal_backend.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public Student findByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }
}
