package com.example.graphql.repository;

import com.example.graphql.model.Article;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface ArticleRepository extends PagingAndSortingRepository<Article, String> {
    Article findArticleById(String id);
}
