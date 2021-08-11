package com.springboot.webmvc.service;

import com.springboot.webmvc.model.Article;
import com.springboot.webmvc.repository.ArticleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<Article> findAllUserArticles(List<String> articleIds) {
        List<Article> articles = new ArrayList<>();
        articleIds.forEach(id -> articles.add(articleRepository.findArticleById(id)));
        return articles;
    }
}
