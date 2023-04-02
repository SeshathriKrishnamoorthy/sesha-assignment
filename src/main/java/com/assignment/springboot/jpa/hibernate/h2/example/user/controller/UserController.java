package com.assignment.springboot.jpa.hibernate.h2.example.user.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.springboot.jpa.hibernate.h2.example.user.entities.User;
import com.assignment.springboot.jpa.hibernate.h2.example.user.exception.UserNotFoundException;
import com.assignment.springboot.jpa.hibernate.h2.example.user.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private final UserService userService;
    
    public UserController( UserService userService) {
        this.userService = userService;
    }
    
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Validated @RequestBody User user) {
        userService.registerUser(user);
        return ResponseEntity.ok("User registered successfully");
    }

@GetMapping("getUserById/{id}")
public ResponseEntity<User> getUserById(@PathVariable Long id)  throws UserNotFoundException {
    Optional<User> user = userService.getUserById(id);
    if (user.isPresent()) {
        return ResponseEntity.ok(user.get());
    } else {
        throw new UserNotFoundException();
    }
}

@GetMapping("getUserByName/{name}")
public ResponseEntity<User> getUserByName(@PathVariable String name)  throws UserNotFoundException {
    Optional<User> user = userService.getUserByName(name);
    if (user.isPresent()) {
        return ResponseEntity.ok(user.get());
    } else {
        throw new UserNotFoundException();
    }
}

}