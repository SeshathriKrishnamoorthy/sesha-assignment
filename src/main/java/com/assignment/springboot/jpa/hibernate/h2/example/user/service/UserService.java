package com.assignment.springboot.jpa.hibernate.h2.example.user.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.springboot.jpa.hibernate.h2.example.user.entities.User;
import com.assignment.springboot.jpa.hibernate.h2.example.user.repository.UserRepository;

@Service
public class UserService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    @Autowired
    UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public User registerUser(User user) {
        User result = userRepository.save(user);
        LOGGER.info("New user registered -> {}", result.getName());
        return result;
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