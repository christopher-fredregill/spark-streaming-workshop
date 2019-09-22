package com.example.spark

import java.time.Instant

import com.typesafe.scalalogging.LazyLogging
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.{DataTypes, StructField, StructType}
import org.apache.spark.sql.functions._

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

    val sightingsSchema = StructType(
      Seq(
        StructField("timestamp",  DataTypes.TimestampType, nullable = false),
        StructField("latitude",   DataTypes.DoubleType, nullable = false),
        StructField("longitude",  DataTypes.DoubleType, nullable = false)
      )
    )
    val sightingsDF = spark.readStream.schema(sightingsSchema).json("s3a://example-bucket/sightings/")
    import spark.implicits._
    sightingsDF
      .withWatermark("timestamp", "1 second")
      .groupBy(
        round('latitude, 0),
        round('longitude, 0),
        window('timestamp, "10 seconds", "5 second")
      )
      .count()
      .orderBy('count desc)
      .writeStream
      .format("console")
      .outputMode("complete")
      .start()
      .awaitTermination()
  }
}
