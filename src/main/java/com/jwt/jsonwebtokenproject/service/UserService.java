package com.jwt.jsonwebtokenproject.service;


import com.jwt.jsonwebtokenproject.model.User;
import com.jwt.jsonwebtokenproject.repository.UserRepository;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

@Service
public class UserService implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("In loadusername");
        User user=userRepository.findByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("User not found.");
        }
        System.out.println("User found");
        System.out.println(user.toString());
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                true,
                true,
                true,
                true,
                Collections.emptyList()
        );
    }


    public UserDetails createUser(@RequestBody User user) {

        User userFound=userRepository.findByUsername(user.getUsername());
        if(userFound!=null){
            throw new UsernameNotFoundException("User already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);
        List<GrantedAuthority> authorityList=new ArrayList<>();

        if(user.getRole().equals("ADMIN")){
            authorityList.add(new SimpleGrantedAuthority("ROLE_"+user.getRole()));
            authorityList.add(new SimpleGrantedAuthority("READ"));
            authorityList.add(new SimpleGrantedAuthority("WRITE"));
        }else if(user.getRole().equals("USER")){
            authorityList.add(new SimpleGrantedAuthority("ROLE_"+user.getRole()));
            authorityList.add(new SimpleGrantedAuthority("READ"));
        }

        System.out.println("New user is created successfully.");

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                true,
                true,
                true,
                true,
                authorityList

        );

    }

    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users=userRepository.findAll();
        return ResponseEntity.status(200).body(users);
    }


    public UserDetails doLogin(@RequestBody User user){

        User founduser=userRepository.findByUsername(user.getUsername());
        System.out.println(founduser.toString());
        if(founduser==null){

            throw new UsernameNotFoundException("User not found.");
        }else{
            List<GrantedAuthority> authorityList=new ArrayList<>();

            String role=founduser.getRole();
            System.out.println(role);
            if(role.equals("ADMIN")){
                authorityList.add(new SimpleGrantedAuthority("ROLE_"+role));
                authorityList.add(new SimpleGrantedAuthority("READ"));
                authorityList.add(new SimpleGrantedAuthority("WRITE"));
            }else if(role.equals("USER")){
                authorityList.add(new SimpleGrantedAuthority("ROLE_"+role));
                authorityList.add(new SimpleGrantedAuthority("READ"));

            }
            System.out.println("getting into password check.");
            System.out.println(passwordEncoder.encode(user.getPassword())+" "+founduser.getPassword());
            String foundPassword=founduser.getPassword();
            String password=user.getPassword();
            System.out.println(passwordEncoder.encode(password).matches(foundPassword));
            if(passwordEncoder.matches(password,foundPassword)){
                System.out.println("Password matched.");
                return new org.springframework.security.core.userdetails.User(
                        user.getUsername(),
                        user.getPassword(),
                        true,
                        true,
                        true,
                        true,
                        authorityList
                );
            }
        }
        throw new UsernameNotFoundException("credentials doesn't match.");

    }

    public User getUser(String username){
        User user=userRepository.findByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }



}
