package com.springboot.webflux.user.controller;

import com.springboot.webflux.user.domain.User;
import com.springboot.webflux.user.services.UserService;

import reactor.core.publisher.Mono;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value="/{userId}")
    public Mono<User> getUserById(@PathVariable("userId") String userId) {
        return userService.getUserById(userId);

    }

    @GetMapping(value="/userName/{userName}")
    public Mono<User> getUserByUserName(@PathVariable("userName") String userName) {
        return userService.getUserByUserName(userName);
    }

    @PostMapping(value = "/")
    public void addUser(@RequestBody Mono<User> user) {
        userService.addUser(user);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public void updateUser(@RequestBody Mono<User> user) {
        userService.updateUser(user);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("userId") String userId) {
        User user = new User();
        user.setId(userId);

        userService.deleteUser(Mono.just(user));
    }

}
