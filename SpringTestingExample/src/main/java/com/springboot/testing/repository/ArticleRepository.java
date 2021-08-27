package com.springboot.testing.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.springboot.testing.model.Article;


public interface ArticleRepository extends PagingAndSortingRepository<Article, String> {
    Article findArticleById(String id);
}
