package com.harshith.quiz_service.dto;

import lombok.Data;

@Data
public class CreateQuiz {

    private int nQ;
    private String title;
    private String category;
}
