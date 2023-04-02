package com.assignment.springboot.jpa.hibernate.h2.example.user.exception;

public class UserNotFoundException extends Exception{

    public UserNotFoundException() {
        super("The user was not found");
    }
    
}
