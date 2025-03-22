package com.services;

import org.springframework.stereotype.Service;

@Service
public interface UserService {
    String register( String email,String password,String fullName);
    String login(String email,String password);
}
