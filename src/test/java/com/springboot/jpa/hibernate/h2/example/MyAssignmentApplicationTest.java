package com.springboot.jpa.hibernate.h2.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.mock.web.MockMultipartFile;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

import com.assignment.springboot.jpa.hibernate.h2.example.MyAssignmentApplication;
import com.assignment.springboot.jpa.hibernate.h2.example.image.entities.Image;
import com.assignment.springboot.jpa.hibernate.h2.example.image.service.ImageService;
import com.assignment.springboot.jpa.hibernate.h2.example.user.entities.User;
import com.assignment.springboot.jpa.hibernate.h2.example.user.service.UserService;
import com.mashape.unirest.http.exceptions.UnirestException;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = MyAssignmentApplication.class)
@SpringBootTest
public class MyAssignmentApplicationTest {
    @Autowired
    UserService userService;

    @Autowired
    ImageService imageService;

    @Test
    public void getUserById() 
    {
       Optional<User> result = userService.getUserById(1001l);
       assertNotNull(result.get());
       assertEquals("John", result.get().getName());
    }

    @Test
    public void getUserByName() 
    {
       Optional<User> result = userService.getUserByName("John");
       assertNotNull(result.get());
       assertEquals("John", result.get().getName());
    }

    @Test
    public void uploadImage() throws IOException, UnirestException
    {
   File file = new File("Broken_Hob.jpg");
   FileInputStream input = new FileInputStream(file);
   MultipartFile multipartFile = new MockMultipartFile("file", file.getName(), "text/plain", IOUtils.toByteArray(input));
   Image image = imageService.uploadImage(multipartFile, 1001l);
   assertNotNull(image);
    }

    @Test
    public void getImageByImageId() throws IOException, UnirestException
    {
   File file = new File("Broken_Hob.jpg");
   FileInputStream input = new FileInputStream(file);
   MultipartFile multipartFile = new MockMultipartFile("file", file.getName(), "image/jpeg", IOUtils.toByteArray(input));
   Image image = imageService.uploadImage(multipartFile, 1001l);
   Optional<Image> result = imageService.getImageByImageId(image.getImageId());
   assertNotNull(result.get());
   assertEquals(image.getImageId(), result.get().getImageId());
    }


    @Test
    public void getImageByUserId() throws IOException, UnirestException
    {
   File file = new File("Broken_Hob.jpg");
   FileInputStream input = new FileInputStream(file);
   MultipartFile multipartFile = new MockMultipartFile("file", file.getName(), "image/jpeg", IOUtils.toByteArray(input));
   Image image = imageService.uploadImage(multipartFile, 1001l);
   List<Optional<Image>> result = imageService.getImagesByUserId(image.getUserId());
   assertNotNull(result.get(0));
   assertEquals(image.getUserId(), result.get(0).get().getUserId());
    }

}
