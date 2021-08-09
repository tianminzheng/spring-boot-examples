package com.example.graphql.datafetcher;

import com.example.graphql.model.User;
import com.example.graphql.service.UserService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AllUsersDataFetcher implements DataFetcher<List<User>> {

    private final UserService userService;

    @Autowired
    AllUsersDataFetcher(UserService userService){
        this.userService = userService;
    }

    @Override
    public List<User> get(DataFetchingEnvironment env) {
        User user =  env.getSource();
        List<User> friends;
        if(user != null) {
            friends = userService.findByIds(user.getFriendsIds());
        }else {
            friends = userService.findAllUsers();
        }
        return friends;
    }
}
