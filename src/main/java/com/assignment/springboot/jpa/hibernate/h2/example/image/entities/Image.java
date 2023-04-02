package com.assignment.springboot.jpa.hibernate.h2.example.image.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;
    private String imageName;
    private String imageRemoteId;
    private String imageLink;
    private Long userId;
    
    public Image() {
        super();
    }
   

    public Image(Long imageId, String imageName, String imageRemoteId, String imageLink, Long userId) {
        this.imageId = imageId;
        this.imageName = imageName;
        this.imageRemoteId = imageRemoteId;
        this.imageLink = imageLink;
        this.userId = userId;
    }


    public Long getImageId() {
        return this.imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public String getImageName() {
        return this.imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageRemoteId() {
        return this.imageRemoteId;
    }

    public void setImageRemoteId(String imageRemoteId) {
        this.imageRemoteId = imageRemoteId;
    }

    public String getImageLink() {
        return this.imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    

    
}