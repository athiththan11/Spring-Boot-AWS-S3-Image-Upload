package com.sample.s3imageupload.imageupload.service;

import com.amazonaws.ResponseMetadata;

import org.springframework.web.multipart.MultipartFile;

public interface IS3Service {

    /**
     * image upload to s3 bucket
     * 
     * @param image multipart image file
     * @return aws reponse meta data
     */
    ResponseMetadata uploadImage(MultipartFile image);

}