package com.learning.aws.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class S3BucketService {

    final AmazonS3Client amazonS3Client;

    public S3BucketService(AmazonS3Client amazonS3Client) {
        this.amazonS3Client = amazonS3Client;
    }

    public void getBucketObjects(String bucketName) {
        ListObjectsRequest listObjectsRequest = new ListObjectsRequest()
                .withBucketName(bucketName);
        ObjectListing objectListing;

        do {
            objectListing = amazonS3Client.listObjects(listObjectsRequest);
            for (S3ObjectSummary objectSummary :
                    objectListing.getObjectSummaries()) {
                System.out.println(" - " + objectSummary.getKey() + "  " +
                        "(size = " + objectSummary.getSize() +
                        ")");
            }
            listObjectsRequest.setMarker(objectListing.getNextMarker());
        } while (objectListing.isTruncated());
    }

    public void uploadFileToBucket() {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("static").getFile() + "/wetrustyou.png");
        PutObjectResult putObjectResult = amazonS3Client.putObject("ahmed-cloudfront-test", "wetrustyou.png", file);
        System.out.println(putObjectResult.getMetadata().toString());
    }


}
