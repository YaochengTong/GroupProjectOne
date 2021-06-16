package com.app.groupprojectapplication.file;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class AmazonS3FileService implements InitializingBean {
    @Value("${custom.aws.access-key}")
    private String accessKey;

    @Value("${custom.aws.secret-key}")
    private String accessSecret;

    @Value("${custom.aws.bucket}")
    private String bucket;

    @Value("${custom.aws.endpoint}")
    private String endpoint;

    private AmazonS3 client;

    @Override
    public void afterPropertiesSet() throws Exception {
        ClientConfiguration config = new ClientConfiguration();
        config.setProtocol(Protocol.HTTP);
        this.client = AmazonS3ClientBuilder
                .standard()
                .withClientConfiguration(config)
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, accessSecret)))
                .withRegion(Regions.US_EAST_2)
                .enablePathStyleAccess()
                .build();
    }

    public void printOutName(){
        ObjectListing objectListing = client.listObjects(this.bucket);
        for(S3ObjectSummary os : objectListing.getObjectSummaries()) {
            System.out.println(os.getKey());
        }
        S3Object s3object = client.getObject(bucket, "template/i983.pdf");
        S3ObjectInputStream inputStream = s3object.getObjectContent();
        try {
            FileUtils.copyInputStreamToFile(inputStream, new File("i983.pdf"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void upload(){
        client.putObject(
                this.bucket,
                "asd.txt",
                new File("C:\\Users\\Administrator\\Desktop\\asd.txt")
        );
    }

    private String upload(InputStream input, String key) throws IOException {
        Date expireDate = new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(30));
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setHttpExpiresDate(expireDate);
        metadata.setContentLength(input.available());

        client.putObject(new PutObjectRequest(bucket, key, input, metadata).withCannedAcl(CannedAccessControlList.PublicRead));
        GeneratePresignedUrlRequest urlRequest = new GeneratePresignedUrlRequest(bucket, key);
        URL url = client.generatePresignedUrl(urlRequest);
        return url.toString();
    }
}
