package com.learning.aws.controller;

import com.learning.aws.service.S3BucketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/bucket")
public class BucketController {
    private final S3BucketService s3BucketService;

    public BucketController(S3BucketService s3BucketService) {
        this.s3BucketService = s3BucketService;
    }

    @PostMapping
    public void postObject() {
        s3BucketService.uploadFileToBucket();
    }


    @GetMapping(path = "/bucket/{bucketName}/objects")
    public void getBucketObjects(@PathVariable("bucketName") String bucketName) {
        s3BucketService.getBucketObjects(bucketName);
    }
}

