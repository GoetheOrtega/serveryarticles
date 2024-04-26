package com.example.spring_11200.services;

import com.example.spring_11200.dto.ArticleDto;
import com.example.spring_11200.dto.ArticleForm;
import com.example.spring_11200.models.Article;
import com.example.spring_11200.models.User;
import com.example.spring_11200.repositores.ArticleRepository;
import com.example.spring_11200.repositores.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.List;


@Component
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private UsersRepository usersRepository;


    @Autowired
    private ArticleRepository articleRepository;
    @Override
    public List<ArticleDto> getByUser(Long userId) {
        User user = usersRepository.getOne(userId);
        List<Article> articlesOfUser = user.getCreatedArticles();
        return ArticleDto.articleList(articlesOfUser);
    }

    @Override
    public ArticleDto addArticle(Long userId, ArticleForm articleForm) {
        int id = 2;
        User author = usersRepository.getOne(userId);

        Article newArticle = Article.builder()
                .author(author)
                .name(articleForm.getName())
                .type(articleForm.getType())
                .text(articleForm.getText())
                .build();

        articleRepository.save(newArticle);
        return ArticleDto.from(newArticle);
    }

    @Override
    public ArticleDto like(Long userId, Long articleId){
        User user = usersRepository.getOne(userId);
        Article article = articleRepository.getOne(articleId);
        if (articleRepository.existsByArticleIdAndLikesContaining(articleId, user)) {
            article.getLikes().remove(user);
        }
        else {
            article.getLikes().add(user);
        }
        articleRepository.save(article);
        return ArticleDto.from(article);
    }
}