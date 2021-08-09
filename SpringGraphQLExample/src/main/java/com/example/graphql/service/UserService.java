package com.example.graphql.service;

import com.example.graphql.model.User;
import com.example.graphql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepo;

    @Autowired
    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public User findUserById(String id) {
        return userRepo.findUserById(id);
    }

    public List<User> findByIds(List<String> ids) {
        List<User> list = new ArrayList<>();
        ids.forEach(id -> list.add(userRepo.findUserById(id)));
        return list;
    }

    public List<User> findAllUsers() {
        return (List<User>) userRepo.findAll();
    }
}
