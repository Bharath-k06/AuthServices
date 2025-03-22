package com.serviceImpl;

import com.entity.User;
import com.repository.UserRepository;
import com.services.UserService;
import com.util.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordUtils passwordUtils;

    @Override
    public  String register( String email, String password, String fullName) {
        User userObj = new User();
        userObj.setEmail(email);
        userObj.setFullName(fullName);
        userObj.setPassword(passwordUtils.hashPassword(password));

        try{
            Optional<User>response = userRepository.findByEmail(email);
            if (response.isPresent())
                return "User Already Exists";
            else{
                userRepository.save(userObj);
                return "Success";
            }

        }
        catch (Exception e){
            System.out.println("Registering error");
        }


        return null;
    }

    @Override
    public String login(String email, String password) {
        try {
            Optional<User> response = userRepository.findByEmail(email);
            User res = response.get();
            System.out.println("password : "+password+"  repo : "+res.getPassword());
            if (passwordUtils.verifyHashedPassword(password,res.getPassword())){
                return "Success in login";
            }
        }
        catch (Exception ex){
            System.out.println("Login error");
        }
        return "Wrong credentials";
    }
}
