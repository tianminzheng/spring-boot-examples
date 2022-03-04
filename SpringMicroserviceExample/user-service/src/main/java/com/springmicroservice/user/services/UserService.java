package com.springmicroservice.user.services;

import com.springmicroservice.user.domain.User;
import com.springmicroservice.user.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
    @Autowired
    private UserRepository userRepository;

    public User getUserById(String userId) {
        
        return userRepository.findById(userId).orElse(null);
    }
    
    public User getUserByUserName(String userName) {
        
        return userRepository.findUserByUserName(userName);
    }

    public void addUser(User user){
        userRepository.save(user);
    }

    public void updateUser(User user){
        userRepository.save(user);
    }

    public void deleteUser(User user){
        userRepository.delete(user);
    }
}
