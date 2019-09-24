package com.example.models

/**
  * Helpers for parsing location Strings such as "30.000 N 20.000 W" into NOAA-compatible latitude and longitude.
  * @author christopher
  * @since 2019-09-17
  */
trait LocationParser {
  /** Extracts the latitude. */
  def parseLatitude(location: String): String = {
    parseLocation(location) match {
      case (n, _) if n.endsWith("N") => n.split(" ")(0).trim()
      case (s, _) if s.endsWith("S") => s"-${s.split(" ")(0)}".trim()
    }
  }

  /** Extracts the longitude. */
  def parseLongitude(location: String): String = {
    parseLocation(location) match {
      case (_, e) if e.endsWith("E") => e.split(" ")(0).trim()
      case (_, w) if w.endsWith("W") => s"-${w.split(" ")(0)}".trim()
    }
  }

  /** Produces a (lat, lon) tuple from an input location string. */
  private def parseLocation(location: String): (String, String) = {
    val latLonRegex = "(\\d+\\.\\d+\\s+[NS])\\s+(\\d+\\.\\d+\\s+[EW])".r
    val latLonRegex(latitude, longitude) = location
    (latitude, longitude)
  }
}
