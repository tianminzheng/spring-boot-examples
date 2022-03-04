package com.springmicroservice.user.controller;

import com.springmicroservice.user.domain.User;
import com.springmicroservice.user.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value="/{userId}")
    public User getUserById(@PathVariable("userId") String userId) {
        return new User(userId, "DemoCode", "DemoName");

    }

    @GetMapping(value="/userName/{userName}")
    public User getUserByUserName(@PathVariable("userName") String userName) {
        return userService.getUserByUserName(userName);
    }

    @PostMapping(value = "/")
    public void addUser(@RequestBody User user) {
        userService.addUser(user);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public void updateUser(@RequestBody User user) {
        userService.updateUser(user);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("userId") String userId) {
        User user = new User();
        user.setId(userId);

        userService.deleteUser(user);
    }

}
