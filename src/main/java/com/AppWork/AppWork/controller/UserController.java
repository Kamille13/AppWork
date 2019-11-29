package com.AppWork.AppWork.controller;

import com.AppWork.AppWork.Util;
import com.AppWork.AppWork.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
        user.setApiKey(Util.hash("Hashpass_CB" + System.currentTimeMillis()));
        return userRepository.save(user);
    }

    @PutMapping("/update/{userId}")
    public User update( @RequestParam String apiKey,
                        @PathVariable Long userId,
                        @RequestBody User userUpdate){
        User userFromDB = userRepository.findById(userId).get();
        if(!userFromDB.getApiKey().equals(apiKey)){
            return null;

        }
        if(userUpdate.getEmail() != null && !userUpdate.getEmail().isEmpty()){
            userFromDB.setEmail(userUpdate.getEmail());
        }
        if(userUpdate.getPassword() != null && !userUpdate.getPassword().isEmpty()){
            userFromDB.setPassword(userUpdate.getPassword());
        }
        return userRepository.save(userFromDB);
    }

    @PostMapping("/users/signIn")
    public User signIn(@RequestBody User user) {
        return userRepository.findUserByEmailAndPasswordIgnoreCase(user.getEmail(),user.getPassword());
    }

    @GetMapping("/users/signIn/{apiKey}")
    public User signInByApiKey(@PathVariable String apiKey) {
        return userRepository.findByApiKey(apiKey);
    }
}
