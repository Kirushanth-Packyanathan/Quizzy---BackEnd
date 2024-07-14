package com.example.quizz_portal_backend.DTO;

import jakarta.persistence.ElementCollection;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@Builder
public class QuestionDTO {
    private Long id;
    private String question;
    private String subject;
    private String questionType;
    private List<String> choices;
    private Set<String> correctAnswers;
}
