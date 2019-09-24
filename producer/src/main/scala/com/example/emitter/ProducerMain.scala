package com.example.emitter

import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.UUID

import akka.actor.{Actor, ActorSystem, Props}
import com.example.models._
import com.typesafe.scalalogging.LazyLogging

import scala.concurrent.duration._
import scala.language.postfixOps
import scala.util.Random

/**
  * Emits [[com.example.models.WhaleSighting]] events.
  * @author christopher
  * @since 2019-09-19
  */
object ProducerMain extends App with LazyLogging with S3Client {
  logger.debug("Starting producer...")
  implicit val system: ActorSystem = akka.actor.ActorSystem("system")
  import system.dispatcher

  class WhaleActor extends Actor {
    def receive: PartialFunction[Any, Unit] = {
      case random: Random => simulateWhaleSighting(random)
      case _              => logger.debug(s"Received something unexpected.")
    }
  }

  val random = new Random()
  val whaleActor = system.actorOf(Props(classOf[WhaleActor]))
  val interval = sys.env.getOrElse("INTERVAL_MILLIS", 500L).toString.toLong
  val whaleEmitter = system.scheduler.schedule(0 milliseconds, Duration(interval, "millis"), whaleActor, random)

  private def simulateWhaleSighting(random: Random): Unit = {
    val whaleType = random.nextDouble() match {
      case u: Double if u < 0.00766057749 => GrayWhale
      case u: Double if u < 0.01502651738 => BlueWhale
      case u: Double if u < 0.03859752504 => HumpbackWhale
      case u: Double if u < 0.05215085445 => FinWhale
      case u: Double if u < 0.07572186211 => SeiWhale
      case u: Double if u < 0.1051856217  => BrydesWhale
      case u: Double if u < 0.2569239835  => MinkeWhale
      case u: Double if u < 0.2613435474  => RightWhale
      case u: Double if u < 0.3674130819  => SpermWhale
      case u: Double if u < 0.4175014732  => Narwhal
      case u: Double if u < 0.432233353   => Orca
      case u: Double if u < 0.4398939305  => FalseKillerWhale
      case u: Double if u < 0.4840895698  => Beluga
      case u: Double if u < 0.6608721273  => BottlenoseDolphin
      case u: Double if u < 0.955509723   => CommonDolphin
      case u: Double if u < 0.9793753683  => SpottedDolphin
      case _                              => DuskyDolphin
    }

    val colorsReported: Seq[WhaleColor] = if (whaleType.colors.size > 1) {
      random.nextDouble() match {
        case u: Double if u < 0.333 => whaleType.colors
        case u: Double if u < 0.667 => Seq(whaleType.colors.head)
        case _                      => Seq(random.shuffle(whaleType.colors.tail).head)
      }
    } else {
      whaleType.colors
    }

    val nostrilsReported: Option[Int] = random.nextDouble() match {
      case u: Double if u < 0.2 => Some(whaleType.nostrils)
      case _                    => None
    }

    val spotsReported: Option[Boolean] = random.nextDouble() match {
      case u: Double if u < 0.85 => Some(whaleType.spots)
      case _                     => None
    }

    val teethReported: Option[Boolean] = random.nextDouble() match {
      case u: Double if u < 0.01  => Some(whaleType.teeth)
      case _                      => None
    }

    val dorsalFinReported: Option[Boolean] = random.nextDouble() match {
      case u: Double if u < 0.5  => Some(whaleType.dorsalFin)
      case u: Double if u < 0.85 => Some(!whaleType.dorsalFin)
      case _                     => None
    }

    val region = whaleType.simulateRegion(random)
    val (latitude, longitude) = region.simulateLocation(random)
    val whaleTypeReported = sys.env.get("REPORT_CLASSIFICATION") match {
      case Some(_: String) => Some(whaleType)
      case _               => None
    }

    val timestampMillisDelayed = sys.env.getOrElse("TIMESTAMP_MILLIS_DELAYED", 0L).asInstanceOf[Long]
    emitSighting(WhaleSighting(
      timestamp = Timestamp.from(Instant.now().minusMillis(timestampMillisDelayed)),
      latitude  = latitude,
      longitude = longitude,
      size      = Some(whaleType.size),
      colors    = colorsReported,
      nostrils  = nostrilsReported,
      spots     = spotsReported,
      teeth     = teethReported,
      dorsalFin = dorsalFinReported,
      whaleType = whaleTypeReported
    ))
  }

  private def emitSighting(sighting: WhaleSighting): Unit = {
    import io.circe._
    import io.circe.syntax._

    val timestampFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    implicit val timestampEncoder: Encoder[Timestamp] = (timestamp: Timestamp) => {
      timestampFormat.format(timestamp).asJson
    }

    implicit val whaleSightingEncoder: Encoder[WhaleSighting] = (sighting: WhaleSighting) => {
      Json.obj(
        "timestamp" -> sighting.timestamp.asJson,
        "latitude"  -> sighting.latitude.asJson,
        "longitude" -> sighting.longitude.asJson,
        "colors"    -> Json.arr(sighting.colors.map(_.label).map(Json.fromString):_*),
        "size"      -> Json.fromString(sighting.size.map(_.label).getOrElse("Unknown")),
        "nostrils"  -> sighting.nostrils.asJson,
        "spots"     -> sighting.spots.asJson,
        "teeth"     -> sighting.teeth.asJson,
        "dorsalFin" -> sighting.dorsalFin.asJson,
        "depth"     -> sighting.depth.asJson,
        "whaleType" -> sighting.whaleType.map(_.name).asJson
      )
    }
    logger.debug(s"Placing whale sighting: $sighting")
    s3.putObject("example-bucket", s"sightings/$executionStart-${UUID.randomUUID()}.json", sighting.asJson.noSpaces)
  }
}
