package com.example.quizz_portal_backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Question cannot be blank")
    private String question;

    @NotBlank(message = "Subject cannot be blank")
    private String subject;

    @NotBlank(message = "Question type cannot be blank")
    private String questionType;

    @ElementCollection
    @NotEmpty
    private Set<@NotBlank String> correctAnswers; // Use Set to ensure unique answers

    @ElementCollection
    private List<@NotBlank String> choices; // Keep List for choices as order might be relevant
}
