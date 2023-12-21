package com.vorstu.DeliveryServiceBackend.config;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.errors.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Configuration
@Slf4j
public class MinioConfig {
    @Value("${minio.access.key}")
    private String accessKey;

    @Value("${minio.access.secret}")
    private String secretKey;

    @Value("${minio.url}")
    private String minioUrl;

    @Value("${minio.bucket}")
    private String bucketName;

    @Bean
    @SneakyThrows
    public MinioClient minioClient() throws IOException, NoSuchAlgorithmException, InvalidKeyException{
        MinioClient client = new MinioClient.Builder()
                .credentials(accessKey, secretKey)
                .endpoint(minioUrl)
                .build();

        boolean bucketFound = client.bucketExists(BucketExistsArgs
                .builder()
                .bucket(bucketName).build());

        if(!bucketFound){
            client.makeBucket(MakeBucketArgs.builder()
                    .bucket(bucketName)
                    .build());
        }
        return client;
    }
}
