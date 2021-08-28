package com.springboot.webflux.user.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.springboot.webflux.user.domain.User;

import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveMongoRepository<User, String>  {

	Mono<User> findUserByUserName(String userName);
}
