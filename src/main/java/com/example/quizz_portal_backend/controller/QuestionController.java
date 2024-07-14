package com.example.quizz_portal_backend.controller;

import com.example.quizz_portal_backend.DTO.QuestionDTO;
import com.example.quizz_portal_backend.entity.Question;
import com.example.quizz_portal_backend.service.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/quizzes")
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping("/createQuestion")
    public ResponseEntity<QuestionDTO> createQuestion(@Valid @RequestBody QuestionDTO question) {
        Question createdQuestion = questionService.createNewQuestion(question);

        QuestionDTO newQuestion = QuestionDTO.builder()
                .question(createdQuestion.getQuestion())
                .questionType(createdQuestion.getQuestionType())
                .choices(createdQuestion.getChoices())
                .correctAnswers(createdQuestion.getCorrectAnswers())
                .subject(createdQuestion.getSubject())
                .build();

        return ResponseEntity.ok(newQuestion);
    }

    @GetMapping("/getAllQuestions")
    public ResponseEntity<List<QuestionDTO>> getAllQuestions() {
        List<Question> questions = questionService.getAllQuestions();
        List<QuestionDTO> questionDTOs = questions.stream()
                .map(question -> QuestionDTO.builder()
                        .id(question.getId())
                        .question(question.getQuestion())
                        .questionType(question.getQuestionType())
                        .choices(question.getChoices())
                        .correctAnswers(question.getCorrectAnswers())
                        .subject(question.getSubject())
                        .build())
                .collect(Collectors.toList());

        return ResponseEntity.ok(questionDTOs);
    }

    @GetMapping("/getQuestionById/{id}")
    public ResponseEntity<QuestionDTO> getQuestionById(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        Optional<Question> theQuestion = questionService.getQuestionById(id);
        if (theQuestion.isPresent()){
            Question question=theQuestion.get();
            QuestionDTO newQuestion = QuestionDTO.builder()
                    .question(question.getQuestion())
                    .questionType(question.getQuestionType())
                    .choices(question.getChoices())
                    .correctAnswers(question.getCorrectAnswers())
                    .subject(question.getSubject())
                    .build();
            return ResponseEntity.ok(newQuestion);
        }else {
            throw new ChangeSetPersister.NotFoundException();
        }
    }

    @PutMapping("/question/{id}/update")
    public ResponseEntity<QuestionDTO> updateQuestion(
            @PathVariable Long id, @RequestBody QuestionDTO question) throws ChangeSetPersister.NotFoundException {
        Question updatedQuestion = questionService.updateQuestion(id, question);
        QuestionDTO newQuestion = QuestionDTO.builder()
                .question(updatedQuestion.getQuestion())
                .questionType(updatedQuestion.getQuestionType())
                .choices(updatedQuestion.getChoices())
                .correctAnswers(updatedQuestion.getCorrectAnswers())
                .subject(updatedQuestion.getSubject())
                .build();
        return ResponseEntity.ok(newQuestion);
    }

    @DeleteMapping("/question/{id}/delete")
    public ResponseEntity<String> deleteQuestion(@PathVariable Long id){
        questionService.deleteQuestion(id);
        return ResponseEntity.ok("Deleted Successfully");
    }

    @GetMapping("/subjects")
    public ResponseEntity<List<String>> getAllSubjects(){
        List<String> subjects = questionService.getAllSubjects();
        return ResponseEntity.ok(subjects);
    }

    @GetMapping("/subjectsByTypes/{subject}")
    public ResponseEntity<List<QuestionDTO>> getAllSubjectsByTypes(@PathVariable String subject){
        List<Question> questions = questionService.getAllSubjectsByTypes(subject);
        List<QuestionDTO> questionDTOs = questions.stream()
                .map(question -> QuestionDTO.builder()
                        .id(question.getId())
                        .question(question.getQuestion())
                        .questionType(question.getQuestionType())
                        .choices(question.getChoices())
                        .correctAnswers(question.getCorrectAnswers())
                        .subject(question.getSubject())
                        .build())
                .collect(Collectors.toList());
        return ResponseEntity.ok(questionDTOs);
    }

    @GetMapping("/questionTypes")
    public ResponseEntity<List<String>> getAllQuestionTypes(){
        List<String> subjects = questionService.getAllQuestionTypes();
        return ResponseEntity.ok(subjects);
    }

    @GetMapping("/quiz/getQuestionsForUser")
    public ResponseEntity<List<QuestionDTO>> getQuestionsForUser(
            @RequestParam Integer numOfQuestions, @RequestParam String subject) {
        List<Question> allQuestions = questionService.getQuestionForUser(numOfQuestions, subject);

        List<Question> mutableQuestions = new ArrayList<>(allQuestions);
        Collections.shuffle(mutableQuestions);

        int availableQuestions = Math.min(numOfQuestions, mutableQuestions.size());
        List<Question> randomQuestions = mutableQuestions.subList(0, availableQuestions);

        List<QuestionDTO> questionDTOs = randomQuestions.stream()
                .map(question -> QuestionDTO.builder()
                        .question(question.getQuestion())
                        .questionType(question.getQuestionType())
                        .choices(question.getChoices())
                        .correctAnswers(question.getCorrectAnswers())
                        .subject(question.getSubject())
                        .build())
                .collect(Collectors.toList());


        return ResponseEntity.ok(questionDTOs);
    }

}
