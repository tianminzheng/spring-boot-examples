package com.example.graphql.repository;

import com.example.graphql.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, String> {
    User findUserById(String id);
}
