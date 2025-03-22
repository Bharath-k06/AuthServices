package com.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordUtils {

    public  BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public  String hashPassword(String password){
        return passwordEncoder.encode(password);
    }
    public  boolean verifyHashedPassword(String rawPassword,String hashedPassword){
            return passwordEncoder.matches(rawPassword,hashedPassword);
    }

}
