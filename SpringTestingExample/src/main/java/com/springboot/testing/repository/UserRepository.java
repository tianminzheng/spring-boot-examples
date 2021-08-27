package com.springboot.testing.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.springboot.testing.model.User;

public interface UserRepository extends PagingAndSortingRepository<User, String> {
    User findUserById(String id);
}
