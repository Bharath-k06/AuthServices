package com.controller;


import com.entity.User;
import com.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class RegisterUser {

    @Autowired
    UserService userService;


    @PostMapping("/login")
    String loginUser(@RequestBody User obj){
        String res = userService.login(obj.getEmail(), obj.getPassword());
        return res;
    }
    @PostMapping ("/register")
    String registerUser(@RequestBody User obj){
        String res=userService.register(obj.getEmail(), obj.getPassword(), obj.getFullName());
        return res;
    }
}
