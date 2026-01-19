package com.harshith.quiz_service.controller;

import com.harshith.quiz_service.dto.CreateQuiz;
import com.harshith.quiz_service.dto.QuestionWrapper;
import com.harshith.quiz_service.dto.QuizResponse;
import com.harshith.quiz_service.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestBody CreateQuiz createQuiz){
        Map<Integer,Integer> map=new HashMap<>();

        quizService.createQuiz(createQuiz.getCategory(), createQuiz.getNQ(), createQuiz.getTitle());
        return new ResponseEntity<>("Working..."+createQuiz.getCategory()+" "+createQuiz.getNQ()+" "+createQuiz.getTitle(), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable Integer id){
        List<QuestionWrapper> quiz=quizService.getQuiz(id);
        if(!quiz.isEmpty()) return ResponseEntity.ok().body(quiz);
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/result/{id}")
    public ResponseEntity<Integer> calculateResult(@PathVariable Integer id,@RequestBody List<QuizResponse> responses){
        int right=quizService.calculateResult(id,responses);
        return ResponseEntity.ok().body(right);
    }

}
