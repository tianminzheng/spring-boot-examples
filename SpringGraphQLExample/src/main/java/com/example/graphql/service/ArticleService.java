package com.example.graphql.service;

import com.example.graphql.model.Article;
import com.example.graphql.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {
    private final ArticleRepository articleRepo;

    @Autowired
    public ArticleService(ArticleRepository articleRepo) {
        this.articleRepo = articleRepo;
    }

    public List<Article> findAllUserArticles(List<String> articleIds) {
        List<Article> articles = new ArrayList<>();
        articleIds.forEach(id -> articles.add(articleRepo.findArticleById(id)));
        return articles;
    }
}
