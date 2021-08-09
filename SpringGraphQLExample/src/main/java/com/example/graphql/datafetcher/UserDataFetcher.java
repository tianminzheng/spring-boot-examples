package com.example.graphql.datafetcher;

import com.example.graphql.model.User;
import com.example.graphql.service.UserService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class UserDataFetcher implements DataFetcher<User> {

    private final UserService userService;

    @Autowired
    UserDataFetcher(UserService userService){
        this.userService = userService;
    }

    @Override
    public User get(DataFetchingEnvironment env) {
        return userService.findUserById(String.valueOf(env.getArguments().get("id")));
    }
}