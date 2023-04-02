package com.assignment.springboot.jpa.hibernate.h2.example.user.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.springboot.jpa.hibernate.h2.example.user.entities.User;
import com.assignment.springboot.jpa.hibernate.h2.example.user.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public User registerUser(User user) {
        userRepository.save(user);
        return user;
    }

    public Optional<User> getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user;
    }

    public Optional<User> getUserByName(String name) {
        Optional<User> user = userRepository.findByName(name);
        return user;
    }


}