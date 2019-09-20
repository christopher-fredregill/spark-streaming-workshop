package com.example.emitter

import java.sql.Timestamp
import java.time.Instant

import akka.actor.{Actor, ActorSystem, Props}
import com.amazonaws.auth.{AWSCredentials, AWSCredentialsProvider}
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.s3.{AmazonS3, AmazonS3ClientBuilder}
import com.example.models.{SpermWhale, WhaleSighting, WhaleType}
import com.typesafe.scalalogging.LazyLogging

import scala.concurrent.duration._
import scala.language.postfixOps

/**
  * Emits [[com.example.models.WhaleSighting]] events.
  * @author christopher
  * @since 2019-09-19
  */
object ProducerMain extends App with LazyLogging {
  logger.debug("Starting producer...")
  implicit val system: ActorSystem = akka.actor.ActorSystem("system")
  import system.dispatcher

  class WhaleActor extends Actor {
    val s3: AmazonS3 = {
      val builder = AmazonS3ClientBuilder.standard()
      builder.setEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://127.0.0.1:9000", "us-west-2"))
      builder.setCredentials(new AWSCredentialsProvider {
        override def getCredentials: AWSCredentials = new AWSCredentials {
          override def getAWSAccessKeyId: String = "minio_access_key"

          override def getAWSSecretKey: String = "minio_secret_key"
        }

        override def refresh(): Unit = {}
      })
      builder.build()
    }

    // TODO: this should encode any whale type correctly
    import io.circe.{Encoder, Json}
    implicit val sightingEncoder: Encoder[WhaleSighting] = (a: WhaleSighting) => Json.obj(
      ("foo", Json.fromString(a.whaleType.get.name)),
      ("bar", Json.fromInt(a.depth.get))
    )

    private def emitSighting(whaleType: WhaleType): Unit = {
      logger.debug(s"Trying to write a $whaleType...")
      import io.circe.syntax._

      // TODO: distribute correctly by geographic area and handle all whale types correctly
      whaleType match {
        case SpermWhale => s3.putObject("example-bucket", s"sightings/${Instant.now().toEpochMilli}", WhaleSighting(
          latitude = 3,
          longitude = 4,
          timestamp = Timestamp.from(Instant.now()),
          size = Some(SpermWhale.size),
          colors = SpermWhale.colors,
          nostrils = Some(SpermWhale.nostrils),
          spots = Some(SpermWhale.spots),
          teeth = Some(SpermWhale.teeth),
          whaleType = Some(SpermWhale)
        ).asJson.noSpaces)
      }
    }

    def receive: PartialFunction[Any, Unit] = {
      case w: WhaleType => emitSighting(w)
      case _            => logger.debug(s"Got something else...")
    }
  }
  val whaleActor = system.actorOf(Props(classOf[WhaleActor]))
  val spermWhaleEmitter = system.scheduler.schedule(0 milliseconds, 500 milliseconds, whaleActor, SpermWhale)
}
