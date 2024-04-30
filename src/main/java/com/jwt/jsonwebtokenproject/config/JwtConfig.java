package com.jwt.jsonwebtokenproject.config;


import org.springframework.context.annotation.Configuration;


import java.io.IOException;

@Configuration

public class JwtConfig {



    private Long expirationTime;

    public Long getExpirationValidity() throws IOException {

        System.out.println("updated jar.");
        return 8640000L;
    }
}
