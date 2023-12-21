package com.vorstu.DeliveryServiceBackend.services;

import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.errors.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
@Slf4j
public class ProductPhotoService {

    @Autowired
    private MinioClient minioClient;

    @Value("${minio.bucket}")
    private String bucketName;

    public InputStream getProductPhoto(String photoUrl) throws InvalidKeyException, IOException, NoSuchAlgorithmException {
        try {
            return minioClient.getObject(GetObjectArgs.builder()
                    .bucket(bucketName)
                    .object(photoUrl)
                    .build())
                    ;
        } catch(MinioException ex){
            log.warn(ex.getMessage());
        } catch(InvalidKeyException | IOException | NoSuchAlgorithmException ex){
            log.warn(ex.getMessage());
        }

        return null;
    }
}
