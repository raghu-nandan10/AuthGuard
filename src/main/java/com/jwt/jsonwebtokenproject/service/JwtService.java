package com.jwt.jsonwebtokenproject.service;


import com.jwt.jsonwebtokenproject.config.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


import io.jsonwebtoken.Jwts;

import java.io.IOException;
import java.util.*;

@Service
public class JwtService {



    private String secretKey="mySecretKeymySecretKeymySecretKeymySecretKeymySecretKey";


//    @Value("${jwt.expirationTime}")
    private Long expirationTime;

    @Autowired
    private JwtConfig jwtConfig;



    @SneakyThrows
    public String generateToken(UserDetails user)  {
        Date now=new Date();
        Date expirationDate=new Date(now.getTime()+jwtConfig.getExpirationValidity());

        Map<String, Object> claims = new HashMap<>();
        claims.put("username", user.getUsername());
        claims.put("password", user.getPassword());

        List<String> authorities=new ArrayList<>();
        for(GrantedAuthority simpleGrantedAuthority:user.getAuthorities()){
            authorities.add(simpleGrantedAuthority.getAuthority());
            System.out.println(simpleGrantedAuthority.getAuthority());
        }
        claims.put("authorities",authorities);


        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256,secretKey)
                .compact();
    }

    public Claims decrypt(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public List<SimpleGrantedAuthority> getRole(String token){
        Claims claims = (Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(token)).getBody();



        List<String> roles = claims.get("authorities", List.class);
        List<SimpleGrantedAuthority> roleAuth=new ArrayList<>();
        for(String entity:roles){
           roleAuth.add(new SimpleGrantedAuthority(entity));
        }
        return roleAuth;
    }
    public String getUsername(String token){
        Claims claims = (Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(token)).getBody();
        return claims.get("username").toString();
    }

}
