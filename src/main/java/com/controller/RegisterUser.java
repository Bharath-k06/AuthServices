package com.controller;


import com.entity.User;
import com.exception.CustomException;
import com.services.UserService;
import com.util.JwtSecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import static com.util.UtilConstants.USER_NAME_JWT;


@RestController
@RequestMapping("/api")
@CrossOrigin
public class RegisterUser {
    private JwtSecurityConfig jwtUtil = null;

    public RegisterUser(JwtSecurityConfig jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Autowired
    UserService userService;

    @PostMapping("/getJwtToken")
    private ResponseEntity<String>getJwtToken(){
        String token = jwtUtil.generateToken();
        return new ResponseEntity<>(token,HttpStatus.OK);
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User request){
        String res = userService.login(request.getEmail(), request.getPassword());
        return new ResponseEntity<>("Success",HttpStatus.OK);
    }

    @Operation(summary = "Register controller for registering", responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Resource not found")
    })
    @PostMapping ("/register")
    String registerUser(@RequestBody User obj){
        String res=userService.register(obj.getEmail(), obj.getPassword(), obj.getFullName());
        return res;
    }

    @Operation(summary = "Testing Rest Template", responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Resource not found")
    })
    @PostMapping("/restTemplateTesting")
    String test1(){
        RestTemplate obj = new RestTemplate();
        System.out.println("In rest template testing");
        String uri = "http://localhost:9899/testingApi/postTest";
        HttpMessageConverter<String> objTest = null;
        String res = obj.postForObject(uri,objTest,String.class);
        return res;
    }
    @Operation(summary = "Testing WebClient", responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Resource not found")
    })
    @GetMapping("/webClientTesting")
    String test2() {
        WebClient client = WebClient.create("http://localhost:9899");


//        Mono<String> response = client.get()
//                .uri("/testingApi/getTest")
//                .retrieve()
//                .bodyToMono(String.class);


        // Or, for a synchronous example (not recommended for reactive applications):
        String blockResponse = client.get()
                .uri("/testingApi/getTest")
                .retrieve()
                .bodyToMono(String.class)
                .block();

        System.out.println("Synchronous Response: " + blockResponse);
        return blockResponse;
    }

    @GetMapping("/testingException/{val}")
    ResponseEntity<String> testException(@PathVariable("val") String val) throws CustomException {
        if(val.equalsIgnoreCase("Yes")){
            throw new CustomException("302","CustomException");
        }
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
}
