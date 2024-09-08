package com.example.todosimple.services;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.todosimple.models.LoginResponse;
import com.example.todosimple.models.User;
import com.example.todosimple.repositories.UserRepository;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder; // Injetando o PasswordEncoder

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDetails user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Usuário não encontrado.");
        }
        return user;
    }

    public LoginResponse login(User user) {
        UserDetails userDetails = loadUserByUsername(user.getEmail());
        if (!passwordEncoder.matches(user.getPassword(), userDetails.getPassword())) {
            throw new RuntimeException("Credenciais inválidas.");
        }
        return new LoginResponse(tokenService.generateToken((User) userDetails), (User) userDetails);
    }

    public Long register(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            return -1L; // Retorna -1 se o usuário já existir
        }
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        User newUser = new User(user.getEmail(), encryptedPassword);
        userRepository.save(newUser);
        return newUser.getId(); // Retorna true se o usuário for salvo com sucesso
    }
}
