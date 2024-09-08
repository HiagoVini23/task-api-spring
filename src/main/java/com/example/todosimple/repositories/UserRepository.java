package com.example.todosimple.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.example.todosimple.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    UserDetails findByEmail(String email);
}