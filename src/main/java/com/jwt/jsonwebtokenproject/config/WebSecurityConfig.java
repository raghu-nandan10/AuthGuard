package com.jwt.jsonwebtokenproject.config;

import com.jwt.jsonwebtokenproject.Role.AppRole;
import com.jwt.jsonwebtokenproject.filter.JwtFilter;
import com.jwt.jsonwebtokenproject.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    public UserDetailsService userDetails(){
        return new UserService();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public JwtFilter jwtFilter(){
        return new JwtFilter();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .addFilterBefore(jwtFilter(),UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/users").hasRole(AppRole.USER.name())
                .antMatchers("/create/user").hasRole(AppRole.ADMIN.name())
                .anyRequest().permitAll();
        return http.build();


    }
}
