package com.harshith.quiz_service.feign;

import com.harshith.quiz_service.dto.QuestionWrapper;
import com.harshith.quiz_service.dto.QuizResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {

    @GetMapping(value = "question/generate")
    public ResponseEntity<List<Integer>> generateQuestions(@RequestParam String category, @RequestParam Integer nQ);

    @PostMapping(value="question/get-questions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> ids);
    @PostMapping(value="question/get-score")
    public ResponseEntity<Integer> getScore(@RequestBody List<QuizResponse> responses);
}
