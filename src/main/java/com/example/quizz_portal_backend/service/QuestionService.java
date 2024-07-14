package com.example.quizz_portal_backend.service;

import com.example.quizz_portal_backend.DTO.QuestionDTO;
import com.example.quizz_portal_backend.entity.Question;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;
import java.util.Optional;

public interface QuestionService {
    Question createNewQuestion(QuestionDTO question);
    List<Question> getAllQuestions();
    Optional<Question> getQuestionById(Long id);
    List<String> getAllSubjects();
    List<String> getAllQuestionTypes();
    Question updateQuestion(Long id,QuestionDTO question);
    void deleteQuestion(Long id);
    List<Question> getQuestionForUser(Integer numOfQuestions, String subject);
    List<Question> getAllSubjectsByTypes(String subject);
}
