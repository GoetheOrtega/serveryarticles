package com.example.spring_11200.dto;

import lombok.Data;

@Data
public class ArticleForm {
    private String name;
    private String type;
    private String text;
}