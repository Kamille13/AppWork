package com.AppWork.AppWork.controller;

import com.AppWork.AppWork.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.AppWork.AppWork.repositery.UserRepository;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    public List<User> getUser(){
        return userRepository.findAll();
    }

    @PostMapping("/users")
    public User postUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PostMapping("/users/signIn")
    public User signIn(@RequestBody User user) {
        return userRepository.findUserByEmailIgnoreCase(user.getEmail());
    }
}
