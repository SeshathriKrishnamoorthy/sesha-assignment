package com.assignment.springboot.jpa.hibernate.h2.example.image.exception;

public class ImageNotFoundException extends Exception{

    public ImageNotFoundException() {
        super("The image was not found");
    }
    
}
