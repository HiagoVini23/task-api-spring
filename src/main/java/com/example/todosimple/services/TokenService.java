package com.example.todosimple.services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.todosimple.models.User;

@Service
public class TokenService {

    private String secret = "segredinho";

    // Método para gerar o token
    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                .withIssuer("auth")
                .withSubject(user.getEmail())
                .withExpiresAt(getExpirationDate())
                .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error while generating token", exception);
        }
    }

    // Método para validar o token
    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                .withIssuer("auth")
                .build()
                .verify(token)
                .getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Error while validating token", exception);
        }
    }

    // Método para obter a data de expiração do token
    private Instant getExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
