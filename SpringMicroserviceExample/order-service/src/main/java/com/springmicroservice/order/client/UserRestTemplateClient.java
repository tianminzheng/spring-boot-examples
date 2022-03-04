package com.springmicroservice.order.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class UserRestTemplateClient {

    @Autowired
    RestTemplate restTemplate;

    public UserMapper getUserById(String userId) {
        ResponseEntity<UserMapper> result =
                restTemplate.exchange("http://gatewayservice/user/users/{userId}", HttpMethod.GET, null,
                        UserMapper.class, userId);

        return result.getBody();
    }


    public UserMapper getUserByUserName(String userName) {

        ResponseEntity<UserMapper> result =
                restTemplate.exchange("http://userservice/users/userName/{userName}", HttpMethod.GET, null,
                        UserMapper.class, userName);

        UserMapper user = result.getBody();
        return user;
    }
}
