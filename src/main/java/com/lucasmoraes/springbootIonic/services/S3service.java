package com.lucasmoraes.springbootIonic.services;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.lucasmoraes.springbootIonic.services.exceptions.FileException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

@Service
public class S3service
{
    private Logger LOG = LoggerFactory.getLogger(S3service.class);

    @Autowired
    private AmazonS3 s3client;

    @Value("${s3.bucket}")
    private String bucketName;

    public URI uploadFile(MultipartFile multipartFile)
    {
        try {
            String fileName = multipartFile.getOriginalFilename();
            InputStream is = multipartFile.getInputStream();
            String contentType = multipartFile.getContentType();
            return uploadFile(is,fileName,contentType);
        } catch (IOException e) {
            throw new FileException("Io error"+e.getMessage());
        }
    }

    public URI uploadFile(InputStream is, String fileName, String contentType)
    {
        try
        {
            ObjectMetadata meta = new ObjectMetadata();
            meta.setContentType(contentType);
            LOG.info("Initiating upload");
            s3client.putObject(bucketName,fileName,is,meta);
            LOG.info("Upload success");
            return s3client.getUrl(bucketName,fileName).toURI();
        }
        catch (URISyntaxException e) {
           throw new FileException("Error converting url to uri");
        }
    }

}
