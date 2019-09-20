package com.example.spark

import java.time.Instant

import com.typesafe.scalalogging.LazyLogging
import org.apache.spark.sql.SparkSession

/**
  * The main entrypoint to our structured streaming application.
  * @author christopher
  * @since 2019-09-17
  */
object ConsumerMain extends App with LazyLogging {

  override def main(args: Array[String]): Unit = {
    val startInstant = Instant.now()
    val outputPath = s"s3a://example-bucket/attempts/$startInstant/"
    // Set up a local SparkSession
    val spark = SparkSession.builder()
      // Lifted from the example core-site.xml file here: https://github.com/minio/cookbook/blob/master/docs/apache-spark-with-minio.md#3-start-spark-shell
      .config("fs.s3a.impl",                "org.apache.hadoop.fs.s3a.S3AFileSystem")
      .config("fs.s3a.endpoint",            "http://127.0.0.1:9000")
      .config("fs.s3a.access.key",          "minio_access_key")
      .config("fs.s3a.secret.key",          "minio_secret_key")
      .config("fs.s3a.path.style.access",   "true")
      .appName("spark-streaming-workshop")
      .master("local[2]")
      .getOrCreate()

    val noaaSensors = spark.read.json("s3a://example-bucket/noaa/sensors/")
    logger.info(s"There are ${noaaSensors.count()} NOAA sensors.")
  }
}
