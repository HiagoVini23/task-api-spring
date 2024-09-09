package com.example.todosimple.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todosimple.models.LoginResponse;
import com.example.todosimple.models.User;
import com.example.todosimple.services.AuthorizationService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthorizationService authorizationService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid User user) {
        try {
            LoginResponse loginResponse = authorizationService.login(user);
            return ResponseEntity.ok(new LoginResponse("Bearer " + loginResponse.getToken(), loginResponse.getUser()));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new LoginResponse(e.getMessage(), null));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody @Valid User user) {
        Long idRegistered = authorizationService.register(user);
        if (idRegistered != -1L) {
            user.setId(idRegistered);
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.badRequest().build(); 
        }
    }
}
