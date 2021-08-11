package com.springboot.webmvc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.webmvc.model.Article;
import com.springboot.webmvc.model.User;
import com.springboot.webmvc.service.ArticleService;
import com.springboot.webmvc.service.UserService;

@RestController
@RequestMapping("/articles")
public class ArticleController {
        
	private ArticleService articleService;
	private UserService userService;
	
    @Autowired
    public ArticleController(ArticleService articleService, UserService userService) {
    	this.articleService = articleService;
    	this.userService = userService;
    }

    @GetMapping(value = "/{userId}")
    public List<Article> getArticlesByUserId(@PathVariable String userId){
    	List<Article> articles = new ArrayList<Article>();
    	
    	User user = userService.findUserById(userId);
    	if(user != null) {
    		articles = articleService.findAllUserArticles(user.getArticlesIds());
    	}
    	
    	return articles;
    }
}
