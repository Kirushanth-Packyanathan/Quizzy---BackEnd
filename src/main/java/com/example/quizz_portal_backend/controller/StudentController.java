package com.example.quizz_portal_backend.controller;

import com.example.quizz_portal_backend.entity.Student;
import com.example.quizz_portal_backend.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/{email}")
    public Student getStudentByEmail(@PathVariable String email) {
        return studentService.findByEmail(email);
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }
}
