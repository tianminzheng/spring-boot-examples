package com.springboot.webflux.user.services;

import com.springboot.webflux.user.domain.User;
import com.springboot.webflux.user.repository.UserRepository;

import reactor.core.publisher.Mono;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
    @Autowired
    private UserRepository userRepository;

    public Mono<User> getUserById(String userId) {
        
        return userRepository.findById(userId);
    }
    
    public Mono<User> getUserByUserName(String userName) {
        
        return userRepository.findUserByUserName(userName);
    }

    public void addUser(Mono<User> user){
    	user.flatMap(userRepository::save);
    }

    public void updateUser(Mono<User> user){
    	user.flatMap(userRepository::save);
    }

    public void deleteUser(Mono<User> user){
    	user.flatMap(userRepository::delete);
    }
}
