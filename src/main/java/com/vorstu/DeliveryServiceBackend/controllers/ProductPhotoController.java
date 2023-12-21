package com.vorstu.DeliveryServiceBackend.controllers;

import com.vorstu.DeliveryServiceBackend.services.ProductPhotoService;
import lombok.SneakyThrows;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@RestController
public class ProductPhotoController {

    @Autowired
    private ProductPhotoService productPhotoService;
    @SneakyThrows
    @GetMapping(value = "/api/photo/{photoUrl}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<Object> getProductPhoto(@PathVariable String photoUrl){
        return ResponseEntity.ok()
                .body(IOUtils.toByteArray(productPhotoService.getProductPhoto(photoUrl)));
    }
}
