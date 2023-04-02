package com.assignment.springboot.jpa.hibernate.h2.example.user.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.springboot.jpa.hibernate.h2.example.user.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findByName(String name);
}