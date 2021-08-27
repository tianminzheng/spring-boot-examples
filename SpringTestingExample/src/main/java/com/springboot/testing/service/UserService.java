package com.springboot.testing.service;

import com.springboot.testing.model.User;
import com.springboot.testing.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUserById(String id) {
        return userRepository.findUserById(id);
    }

    public List<User> findByIds(List<String> ids) {
        List<User> list = new ArrayList<>();
        ids.forEach(id -> list.add(userRepository.findUserById(id)));
        return list;
    }

    public List<User> findAllUsers() {
        return (List<User>) userRepository.findAll();
    }
}
