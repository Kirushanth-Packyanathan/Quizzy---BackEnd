package com.example.quizz_portal_backend.service;

import com.example.quizz_portal_backend.DTO.QuestionDTO;
import com.example.quizz_portal_backend.entity.Question;
import com.example.quizz_portal_backend.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService{
    private final QuestionRepository questionRepository;


    @Override
    public Question createNewQuestion(QuestionDTO questionDTO) {
        Question question = new Question();
        question.setQuestion(questionDTO.getQuestion());
        question.setSubject(questionDTO.getSubject());
        question.setQuestionType(questionDTO.getQuestionType());
        question.setChoices(questionDTO.getChoices());
        question.setCorrectAnswers(questionDTO.getCorrectAnswers());

        return questionRepository.save(question);
    }

    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public Optional<Question> getQuestionById(Long id) {
        return questionRepository.findById(id);
    }

    @Override
    public List<String> getAllSubjects() {
        return questionRepository.findDistinctSubjects();
    }

    @Override
    public List<String> getAllQuestionTypes() {
        return questionRepository.findDistinctQuestionTypes();
    }

    @Override
    public Question updateQuestion(Long id, QuestionDTO question) {
        Optional<Question> toBeUpdatedQuestion=getQuestionById(id);
        if (toBeUpdatedQuestion.isPresent()){
            Question updatedQuestion= toBeUpdatedQuestion.get();
            updatedQuestion.setQuestion(question.getQuestion());
//            updatedQuestion.setQuestionType(question.getQuestionType());
            updatedQuestion.setChoices(question.getChoices());
            updatedQuestion.setCorrectAnswers(question.getCorrectAnswers());

            return questionRepository.save(updatedQuestion);
        }
        else {
            try {
                throw new ChangeSetPersister.NotFoundException();
            } catch (ChangeSetPersister.NotFoundException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Override
    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }

    @Override
    public List<Question> getQuestionForUser(Integer numOfQuestions, String subject) {
        Pageable pageable= PageRequest.of(0,numOfQuestions);
        return questionRepository.findBySubject(subject,pageable).getContent();
    }

    @Override
    public List<Question> getAllSubjectsByTypes(String subject) {
        return questionRepository.findDistinctSubjectsByTypes(subject);
    }

}
