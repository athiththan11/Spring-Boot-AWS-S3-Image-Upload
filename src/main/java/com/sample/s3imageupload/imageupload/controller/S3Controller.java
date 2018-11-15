package com.sample.s3imageupload.imageupload.controller;

import com.amazonaws.ResponseMetadata;
import com.sample.s3imageupload.imageupload.service.IS3Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class S3Controller {

    @Autowired
    private IS3Service s3service;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public @ResponseBody ResponseMetadata uploadImage(@RequestParam(value = "image") MultipartFile image) {
        return this.s3service.uploadImage(image);
    }

}