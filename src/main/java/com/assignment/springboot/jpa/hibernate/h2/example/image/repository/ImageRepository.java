package com.assignment.springboot.jpa.hibernate.h2.example.image.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.springboot.jpa.hibernate.h2.example.image.entities.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Optional<Image>> findAllByUserId(Long userId);
    
}