package com.example.quizz_portal_backend.DTO;

import lombok.Data;

@Data
public class StudentDTO {
    private Long id;
    private String name;
    private String email;
    // Exclude password from DTO for security reasons
}

