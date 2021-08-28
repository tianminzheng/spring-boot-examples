package com.springboot.webflux.order.client;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class UserWebClient {

    public Mono<UserMapper> getUserById(String userId) {
    	
        Mono<UserMapper> userFromRemote = WebClient.create()
    			.get()
    			.uri("http://127.0.0.1:8082/users/{userId}", userId) 
    			.retrieve()
    			.bodyToMono(UserMapper.class); 
        
        return userFromRemote;        
    }


    public Mono<UserMapper> getUserByUserName(String userName) {

        Mono<UserMapper> userFromRemote = WebClient.create()
    			.get()
    			.uri("http://127.0.0.1:8082/users/userName/{userName}", userName) 
    			.retrieve()
    			.bodyToMono(UserMapper.class); 
        
        return userFromRemote;      
    }
}
