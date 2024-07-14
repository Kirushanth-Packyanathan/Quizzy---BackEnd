package com.example.quizz_portal_backend.repository;

import com.example.quizz_portal_backend.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.net.ContentHandler;
import java.util.List;

public interface QuestionRepository extends JpaRepository<Question,Long> {
    @Query("SELECT DISTINCT q.subject from Question q")
    List<String> findDistinctSubjects();

    Page<Question> findBySubject(String subject, Pageable pageable);

    @Query("SELECT DISTINCT q.questionType from Question q")
    List<String> findDistinctQuestionTypes();

    @Query("SELECT DISTINCT q FROM Question q WHERE q.subject = :subject")
    List<Question> findDistinctSubjectsByTypes(@Param("subject") String subject);

}
