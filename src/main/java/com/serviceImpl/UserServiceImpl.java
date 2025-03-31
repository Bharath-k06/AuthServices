package com.serviceImpl;

import com.entity.AuthAuditEvent;
import com.entity.User;
import com.repository.UserRepository;
import com.services.UserService;
import com.streamService.AuthLogProducer;
import com.util.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordUtils passwordUtils;

    @Autowired
    private AuthLogProducer authLogProducer;

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
                AuthAuditEvent event = new AuthAuditEvent(email, LocalDateTime.now(),"Success","KumarLaptop","testing");
                authLogProducer.sendAuditEvent(event);
                return "Success in login";
            }
            else{
                AuthAuditEvent event = new AuthAuditEvent(email, LocalDateTime.now(),"Failure","KumarLaptop","testing");
                authLogProducer.sendAuditEvent(event);

            }
        }
        catch (Exception ex){
            System.out.println("Login error");
        }
        return "Wrong credentials";
    }
}
