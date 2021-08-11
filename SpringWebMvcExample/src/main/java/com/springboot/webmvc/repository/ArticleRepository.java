package com.springboot.webmvc.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.springboot.webmvc.model.Article;


public interface ArticleRepository extends PagingAndSortingRepository<Article, String> {
    Article findArticleById(String id);
}
