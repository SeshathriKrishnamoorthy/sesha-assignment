package com.assignment.springboot.jpa.hibernate.h2.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.assignment.springboot.jpa.hibernate.h2.example.user.repository.UserRepository;

@SpringBootApplication
public class MyAssignmentApplication implements CommandLineRunner {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserRepository repository;

    @Override
    public void run(String... args) {

        

        LOGGER.info("All users -> {}", repository.findAll());
    }

    public static void main(String[] args) {
        
        SpringApplication.run(MyAssignmentApplication.class, args);
    }

}
