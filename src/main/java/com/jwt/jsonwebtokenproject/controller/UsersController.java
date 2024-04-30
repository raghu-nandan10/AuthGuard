package com.jwt.jsonwebtokenproject.controller;



import com.jwt.jsonwebtokenproject.Response.Response;
import com.jwt.jsonwebtokenproject.model.User;
import com.jwt.jsonwebtokenproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
public class UsersController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getProducts(){
        System.out.println("getting users");
        return userService.getAllUsers();
    }


//    @GetMapping("/users/")
//    public ResponseEntity<List<User>> getSpecificProduct(@RequestParam int id){
//        System.out.println("Getting specific product.");
//        return userService.getAllUsers();
//    }


    @PostMapping("/create/user")
    public ResponseEntity<Response> createUser(@RequestBody User user){
        UserDetails userDetails=userService.createUser(user);

        return ResponseEntity.status(200).body(new Response("user created successfully.", Arrays.asList(userDetails)));
    }






}
