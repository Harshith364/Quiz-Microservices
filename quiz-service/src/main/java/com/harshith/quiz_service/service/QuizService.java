package com.harshith.quiz_service.service;
import com.harshith.quiz_service.dto.QuestionWrapper;
import com.harshith.quiz_service.dto.QuizResponse;
import com.harshith.quiz_service.entity.Question;
import com.harshith.quiz_service.entity.Quiz;
import com.harshith.quiz_service.feign.QuizInterface;
import com.harshith.quiz_service.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuizInterface quizInterface;

    public void createQuiz(String category,int nQ,String title){
        List<Integer> questions=quizInterface.generateQuestions(category,nQ).getBody();

        Quiz quiz=Quiz.builder()
                .title(title)
                .questions(questions)
                .build();

        quizRepository.save(quiz);
    }

    public List<QuestionWrapper> getQuiz(Integer id) {
        Optional<Quiz> quiz=quizRepository.findById(id);
        ResponseEntity<List<QuestionWrapper>> questionsFromQS=quizInterface.getQuestionsFromId(quiz.get().getQuestions());
        List<QuestionWrapper> questionsForUser=questionsFromQS.getBody();
        return questionsForUser;

    }

    public int calculateResult(Integer id, List<QuizResponse> responses) {
        ResponseEntity<Integer> result=quizInterface.getScore(responses);
        return result!=null ? result.getBody():null;
    }
}
