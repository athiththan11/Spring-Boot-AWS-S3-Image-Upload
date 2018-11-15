package com.sample.s3imageupload.imageupload.service.impl;

import java.io.IOException;
import java.util.HashMap;

import javax.annotation.PostConstruct;

import com.amazonaws.ResponseMetadata;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.sample.s3imageupload.imageupload.service.IS3Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class S3Service implements IS3Service {

    @Value("${s3.aws.access_key_id}")
    private String accessKey;

    @Value("${s3.aws.secret_access_key}")
    private String secretKey;

    @Value("${s3.region}")
    private String region;

    @Value("${s3.bucket}")
    private String bucketName;

    private AmazonS3 s3Client;

    /**
     * intiialize amazon bucket for connection
     */
    @PostConstruct
    private void initAmazon() {
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
        this.s3Client = AmazonS3ClientBuilder.standard().withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).build();
    }

    @Override
    public ResponseMetadata uploadImage(MultipartFile image) {

        HashMap<String, String> metadata = new HashMap<>();
        ResponseMetadata responseMetadata = new ResponseMetadata(metadata);

        if (!image.isEmpty()) {
            try {
                ObjectMetadata objectMetadata = new ObjectMetadata();
                objectMetadata.setContentType(image.getContentType());

                this.s3Client.putObject(new PutObjectRequest(bucketName, image.getOriginalFilename(),
                        image.getInputStream(), objectMetadata));

                metadata.put("message", "success");
                metadata.put("status", "200");

            } catch (IOException io) {

                metadata.put("message", "error");
                metadata.put("status", "500");

            }
        }

        return responseMetadata;
    }

}