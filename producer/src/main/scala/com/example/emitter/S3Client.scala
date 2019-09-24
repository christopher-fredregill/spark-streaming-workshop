package com.example.emitter

import com.amazonaws.auth.{AWSCredentials, AWSCredentialsProvider}
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.s3.{AmazonS3, AmazonS3ClientBuilder}
import minio.MinioEnv

/**
  * @author christopher
  * @since 2019-09-22
  */
trait S3Client extends MinioEnv {
  val s3: AmazonS3 = {
    val builder = AmazonS3ClientBuilder.standard()
    builder.setEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(minioEndpoint, "us-west-2"))
    builder.setCredentials(new AWSCredentialsProvider {
      override def getCredentials: AWSCredentials = new AWSCredentials {
        override def getAWSAccessKeyId: String = minioAccessKey
        override def getAWSSecretKey: String = minioSecretKey
      }
      override def refresh(): Unit = {}
    })
    builder.build()
  }
}
