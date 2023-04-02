package com.assignment.springboot.jpa.hibernate.h2.example.image.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.assignment.springboot.jpa.hibernate.h2.example.image.entities.Image;
import com.assignment.springboot.jpa.hibernate.h2.example.image.repository.ImageRepository;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;



@Service
public class ImageService {
    @Value("${imgur.clientId}")
    private String clientId;
    @Value("${imgur.endpoint}")
    private String endpoint;
    @Autowired
    ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository){
        this.imageRepository = imageRepository;
    }
    public Image uploadImage(MultipartFile file, Long uploadedBy) throws IOException, UnirestException {
      
    Image image = new Image();
    // Create a new HTTP request to upload the file to Imgur
    File convFile = convert(file);

    // Create a new HTTP request to upload the file to Imgur
    
        HttpResponse<JsonNode> response = Unirest.post(endpoint)
            .header("Authorization", "Client-ID " + clientId)
            .field("image", convFile)
            .asJson();

    // Parse the JSON response to get the link to the uploaded image
    JSONObject data = response.getBody().getObject().getJSONObject("data");
    String link = data.getString("link");
    String imgurId = data.getString("id");
    // Create a new Image object with the filename and Imgur link
    
    image.setImageName(file.getOriginalFilename());
    image.setImageLink(link);
    image.setImageRemoteId(imgurId);
    image.setUserId(uploadedBy);

    // Save the Image object to the database
    imageRepository.save(image);

    return image;
    }


    public Optional<Image> getImageByImageId(Long id) {
        // retrieve the image from the database
        Optional<Image> image= imageRepository.findById(id);
        return image;
    }

    public List<Optional<Image>> getImagesByUserId(Long id) {
        // retrieve the image from the database
        List<Optional<Image>> images= imageRepository.findAllByUserId(id);
        return images;
    }

   

    public File convert(MultipartFile file) {
        File convFile = new File(file.getOriginalFilename());
        try {
            convFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(file.getBytes());
            fos.close(); //IOUtils.closeQuietly(fos);
        } catch (IOException e) {
            convFile = null;
        }

        return convFile;
    }

    public void deleteImagesByUserId(Long id)  {
        // delete the image from Imgur's servers
        List<Optional<Image>> images= imageRepository.findAllByUserId(id);
        for (Optional<Image> image : images) {
            if(image.isPresent())
                try {
                    deleteImageFromImgur(image.get().getImageRemoteId());
                } catch (UnirestException e) 
                {
                }
        }
        // delete the Image object from the database
    }

    public void deleteImageFromImgur(String imageId) throws UnirestException {
        // set the Imgur client ID and API endpoint
        String endpoint = "https://api.imgur.com/3/image/" + imageId;
    
        // create a HTTP request to the Imgur API endpoint
        HttpResponse<String> response = Unirest.delete(endpoint)
            .header("Authorization", "Client-ID " + clientId)
            .asString();
    
        // check if the request was successful
        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed to delete image from Imgur");
        }
    }

    
}