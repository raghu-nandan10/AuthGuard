package com.jwt.jsonwebtokenproject.AuthProviders;

import com.jwt.jsonwebtokenproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


@Component
public class CustomAuthProvider implements AuthenticationProvider {
    @Autowired
    UserService userService;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println("custom authentication provider method is called.");
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        UserDetails user =  userService.loadUserByUsername(username);

        if (passwordMatches(password, user.getPassword())) {
            return new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());
        } else {
            throw new BadCredentialsException("Invalid username or password");
        }


    }
    private boolean passwordMatches(String rawPassword, String encodedPassword) {

        return rawPassword.equals(encodedPassword);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
