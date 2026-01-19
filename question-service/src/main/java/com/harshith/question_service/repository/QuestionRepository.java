package com.harshith.question_service.repository;

import com.harshith.question_service.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Integer> {

    List<Question> findByCategory(String cat);

    @Query(value="SELECT q.id FROM questions q WHERE q.category=:category ORDER BY RANDOM() LIMIT :nQ",nativeQuery = true)
    List<Integer> findRandomQuestionsByCategory(String category, int nQ);
}
