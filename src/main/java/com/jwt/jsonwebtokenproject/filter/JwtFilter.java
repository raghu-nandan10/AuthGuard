package com.jwt.jsonwebtokenproject.filter;

import com.jwt.jsonwebtokenproject.service.JwtService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JwtService jwtService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader=request.getHeader("Authorization");
        if(StringUtils.hasText(authHeader)){
            String jwtToken=authHeader;
            Authentication authentication  = new UsernamePasswordAuthenticationToken(
                    jwtService.getUsername(jwtToken),null,jwtService.getRole(jwtToken)
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request,response);
    }
}
