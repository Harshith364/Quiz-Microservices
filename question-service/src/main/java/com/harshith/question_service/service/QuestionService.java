package com.harshith.question_service.service;

import com.harshith.question_service.dto.QuestionWrapper;
import com.harshith.question_service.dto.QuizResponse;
import com.harshith.question_service.entity.Question;
import com.harshith.question_service.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public List<Question> getAllQuestionsByCategory(String type) {
        return questionRepository.findByCategory(type);
    }

    public Question addQuestion(Question question) {
        return questionRepository.save(question);
    }

    public void deleteQuestion(int id) {
        questionRepository.deleteById(id);
    }

    public List<Integer> generateQuestions(String category, Integer nQ) {
        return questionRepository.findRandomQuestionsByCategory(category,nQ);
    }

    public Integer getScore(List<QuizResponse> responses) {
        int right=0;
        for(QuizResponse response:responses){
            Question question=questionRepository.findById(response.getId()).get();
            if(response.getResponse().equals(question.getRightAnswer())){
                right++;
            }
        }
        return right;
    }

    public List<QuestionWrapper> getQuestionsFromId(List<Integer> ids) {
        List<QuestionWrapper> wrappers=new ArrayList<>();
        for(Integer id:ids){
            Question question=questionRepository.findById(id).get();
            QuestionWrapper questionWrapper=new QuestionWrapper();
            questionWrapper.setId(question.getId());
            questionWrapper.setQuestionTitle(question.getQuestionTitle());
            questionWrapper.setOption1(question.getOption1());
            questionWrapper.setOption2(question.getOption2());
            questionWrapper.setOption3(question.getOption3());
            questionWrapper.setOption4(question.getOption4());
            wrappers.add(questionWrapper);
        }
        return wrappers;
    }
}
