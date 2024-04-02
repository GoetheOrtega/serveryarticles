package com.example.spring_11200.services;

import com.example.spring_11200.dto.ArticleDto;
import com.example.spring_11200.dto.ArticleForm;

import java.util.List;

public interface ArticleService {
    List<ArticleDto> getByUser(Long id);

    ArticleDto addArticle(Long userId, ArticleForm articleForm);

    ArticleDto like(Long userId, Long articleId);
}