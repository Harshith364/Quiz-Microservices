package com.harshith.question_service.controller;

import com.harshith.question_service.dto.QuestionWrapper;
import com.harshith.question_service.dto.QuizResponse;
import com.harshith.question_service.entity.Question;
import com.harshith.question_service.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/question")
@Slf4j
public class QuestionController {

    @Autowired
    QuestionService questionService;


    @GetMapping("/all")
    public ResponseEntity<List<Question>> getAllQuestions(){
        try {
            return new ResponseEntity<>(questionService.getAllQuestions(), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping(value="/category/{type}")
    public ResponseEntity<List<Question>> getAllQuestionsByCategory(@PathVariable String type){
        try {
            return new ResponseEntity<>(questionService.getAllQuestionsByCategory(type), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        try {
            Question q = questionService.addQuestion(question);
            if (q != null) return new ResponseEntity<>("Question added successfully",HttpStatus.CREATED);

        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable int id){
        try{
            questionService.deleteQuestion(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping(value = "/generate")
    public ResponseEntity<List<Integer>> generateQuestions(@RequestParam String category,@RequestParam Integer nQ){
        List<Integer> questions=questionService.generateQuestions(category,nQ);
        return new ResponseEntity<>(questions,HttpStatus.OK);
    }

    @PostMapping(value="/get-questions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> ids){
        List<QuestionWrapper> wrappers=questionService.getQuestionsFromId(ids);
        HashMap<Integer,Integer> map=new HashMap<>(values().length,1);

        return ResponseEntity.ok().body(wrappers);
    }

    @PostMapping(value="/get-score")
    public ResponseEntity<Integer> getScore(@RequestBody List<QuizResponse> responses){
        Integer score=questionService.getScore(responses);
        return ResponseEntity.ok().body(score);
    }
}
