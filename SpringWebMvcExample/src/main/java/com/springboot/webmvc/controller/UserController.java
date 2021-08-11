package com.springboot.webmvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.webmvc.model.User;
import com.springboot.webmvc.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
        
	private UserService userService;
	
    @Autowired
    public UserController(UserService userService) {
    	this.userService = userService;
    }

    @GetMapping(value = "/{id}")
    public User getUserById(@PathVariable String id){
        return userService.findUserById(id);
    }
    
    @GetMapping(value = "")
    public List<User> getAllUsers(){
        return userService.findAllUsers();
    }
}
