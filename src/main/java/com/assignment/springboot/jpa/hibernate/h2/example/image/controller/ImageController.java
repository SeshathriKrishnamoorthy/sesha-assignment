package com.assignment.springboot.jpa.hibernate.h2.example.image.controller;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.assignment.springboot.jpa.hibernate.h2.example.image.entities.Image;
import com.assignment.springboot.jpa.hibernate.h2.example.image.exception.ImageNotFoundException;
import com.assignment.springboot.jpa.hibernate.h2.example.image.service.ImageService;
import com.mashape.unirest.http.exceptions.UnirestException;

@RestController
@RequestMapping("/images")
public class ImageController {

    @Autowired
    private final ImageService imageService;
    
    public ImageController( ImageService imageService) {
        this.imageService = imageService;
    }
    
    @PostMapping
    @RequestMapping("/uploadImage")
    public ResponseEntity<Image> uploadImage(@RequestParam("file") MultipartFile file, @RequestParam("userId") Long id) throws SQLException {
        try {
            // call the uploadImage method to upload the file to Imgur and save the image to the database
            Image image = imageService.uploadImage(file,id);

            // return a response entity with the saved Image object and a HTTP status of 201 (created)
            return new ResponseEntity<>(image, HttpStatus.CREATED);
        } catch (IOException | UnirestException e) {
            // if an error occurs during the upload or save process, return a HTTP status of 500 (internal server error)
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping("/getImageByImageId/{id}")
    public ResponseEntity<Image> getImageByImageId(@PathVariable Long id) throws ImageNotFoundException {
        Optional<Image> image = imageService.getImageByImageId(id);
        if (image.isPresent()) {
            return ResponseEntity.ok(image.get());
        } else {
            throw new ImageNotFoundException();
        }
    }

    @RequestMapping("/getImagesByUserId/{id}")
    public ResponseEntity<List<Optional<Image>>> getImageByUserId(@PathVariable Long id) {
        List<Optional<Image>> images = imageService.getImagesByUserId(id);
        return ResponseEntity.ok(images);
    }

    
    @RequestMapping("/deleteImagesByUserId{id}")
    public void deleteImagesByUserId(@PathVariable Long id) throws UnirestException {
         imageService.deleteImagesByUserId(id);
    }

    

    


}