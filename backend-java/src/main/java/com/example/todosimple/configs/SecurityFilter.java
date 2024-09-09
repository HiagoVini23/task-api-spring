package com.example.todosimple.configs;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.todosimple.repositories.UserRepository;
import com.example.todosimple.services.TokenService;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;


@Component
public class SecurityFilter extends OncePerRequestFilter{
    @Autowired 
    TokenService tokenService;

    @Autowired
    UserRepository userRepository;
    
    @Override
    protected void doFilterInternal(javax.servlet.http.HttpServletRequest request,
            javax.servlet.http.HttpServletResponse response, javax.servlet.FilterChain filterChain)
            throws javax.servlet.ServletException, IOException {
                String token = this.recoverToken(request);
    
                if (token != null) {
                    var username = tokenService.validateToken(token);
                    UserDetails user = userRepository.findByEmail(username);
            
                    if (user != null) {
                        var authentication = new UsernamePasswordAuthenticationToken(user, token, user.getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
                filterChain.doFilter(request, response);
    }

    private String recoverToken(javax.servlet.http.HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }

}