package com.jwt.jsonwebtokenproject.controller;


import com.jwt.jsonwebtokenproject.Response.Response;
import com.jwt.jsonwebtokenproject.Response.Token;
import com.jwt.jsonwebtokenproject.model.User;
import com.jwt.jsonwebtokenproject.service.JwtService;
import com.jwt.jsonwebtokenproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private UserService userService;


    @Autowired
    private JwtService jwtService;


    @PostMapping("/register")
    public ResponseEntity<Response> doRegister(@RequestBody User user){
        UserDetails registeredUser=userService.createUser(user);

        String jwtToken=jwtService.generateToken(registeredUser);
       return ResponseEntity.status(200).body(new Response("user registered successfully", Arrays.asList(registeredUser)));
    }

    @PostMapping("/login/user")
    public ResponseEntity<Response> doLogin(@RequestBody User user){

        UserDetails loggedUser=userService.doLogin(user);
        String jwtToken=jwtService.generateToken(loggedUser);
        return ResponseEntity.status(200).body(new Response("logged in successfully",Arrays.asList(new Token(jwtToken))));
    }
}
