package com.example.quizz_portal_backend.controller;

import com.example.quizz_portal_backend.entity.Student;
import com.example.quizz_portal_backend.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/{email}")
    public ResponseEntity<?> getStudentByEmail(@PathVariable String email) {
        Student student = studentService.findByEmail(email);
        if (student != null) {
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
        }
    }

    @PostMapping
    public ResponseEntity<?> createStudent(@RequestBody Student student) {
        try {
            // Check if a student with the same email already exists
            if (studentService.existsByEmail(student.getEmail())) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("A student with this email already exists.");
            }

            Student savedStudent = studentService.saveStudent(student);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
        } catch (Exception e) {
            // Log the error for debugging
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while saving the student.");
        }
    }
}
