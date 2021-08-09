package com.example.graphql;

import com.example.graphql.model.Article;
import com.example.graphql.model.User;
import com.example.graphql.repository.ArticleRepository;
import com.example.graphql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

@Component
public class DataIinitialization {
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;

    @Autowired
    public DataIinitialization(UserRepository userRepository, ArticleRepository articleRepository) {
        this.userRepository = userRepository;
        this.articleRepository = articleRepository;
    }

    @PostConstruct
    public void loadData() {
        if (((ArrayList<User>) userRepository.findAll()).size() != 0) return;

        ArrayList<User> users = new ArrayList<>(Arrays.asList(new User(), new User(), new User(), new User()));
        String[] names = {"Name1", "Name2", "Na3me", "Name4"};
        int[] ages = {24, 24, 53, 14};
        String[] nations = {"China", "America", "Japan", "Korea"};

        for (int i = 0; i < 4; ++i) {
            users.get(i).setName(names[i]);
            users.get(i).setAge(ages[i]);
            users.get(i).setCreatedAt(new Date());
            users.get(i).setArticlesIds(new ArrayList<>());
            users.get(i).setFriendsIds(new ArrayList<>());
            users.get(i).setNationality(nations[i]);
        }

        users = (ArrayList<User>) userRepository.saveAll(users);

        ArrayList<Article> articles = new ArrayList<>();
        String[] titles = {
                "Title1",
                "Title2",
                "Title3"
        };
        int[] minutes = { 8, 20, 17 };
        String[] authorIds = {users.get(0).getId(), users.get(1).getId(), users.get(3).getId()};

        for (int i = 0; i < 3; ++i) {
            Article article = new Article();
            article.setTitle(titles[i]);
            article.setMinutesRead(minutes[i]);
            article.setAuthorId(authorIds[i]);
            articles.add(article);
        }

        articles = (ArrayList<Article>) articleRepository.saveAll(articles);

        users.get(0).setArticlesIds(Collections.singletonList(articles.get(1).getId()));
        users.get(1).setArticlesIds(Collections.singletonList(articles.get(2).getId()));
        users.get(3).setArticlesIds(Collections.singletonList(articles.get(0).getId()));

        users = (ArrayList<User>) userRepository.saveAll(users);

        User u = users.get(0);
        ArrayList<String> friends = new ArrayList<>();
        for (int i = 1; i < 4; ++i)
            friends.add(users.get(i).getId());
        u.setFriendsIds(friends);
        userRepository.save(u);
    }
}
