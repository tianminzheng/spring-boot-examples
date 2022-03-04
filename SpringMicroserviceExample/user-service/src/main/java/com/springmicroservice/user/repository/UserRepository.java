package com.springmicroservice.user.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.springmicroservice.user.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>  {

	User findUserByUserName(String userName);
}
