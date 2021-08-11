package com.springboot.webmvc.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.springboot.webmvc.model.User;

public interface UserRepository extends PagingAndSortingRepository<User, String> {
    User findUserById(String id);
}
