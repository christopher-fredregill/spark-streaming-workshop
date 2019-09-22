package com.example.emitter

import com.amazonaws.auth.{AWSCredentials, AWSCredentialsProvider}
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.s3.{AmazonS3, AmazonS3ClientBuilder}

/**
  * @author christopher
  * @since 2019-09-22
  */
trait S3Client {
  val s3: AmazonS3 = {
    val builder = AmazonS3ClientBuilder.standard()
    val endpoint = s"http://${sys.env.getOrElse("MINIO_HOST", "127.0.0.1")}:${sys.env.getOrElse("MINIO_PORT", 9000)}"
    builder.setEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endpoint, "us-west-2"))
    builder.setCredentials(new AWSCredentialsProvider {
      override def getCredentials: AWSCredentials = new AWSCredentials {
        override def getAWSAccessKeyId: String = "minio_access_key"
        override def getAWSSecretKey: String = "minio_secret_key"
      }
      override def refresh(): Unit = {}
    })
    builder.build()
  }
}
