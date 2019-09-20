package com.example.models

import java.sql.Timestamp

/**
  * A model class representing a sighting of an animal at a given time, in a given geographic location.
  * @author christopher
  * @since 2019-09-19
  */
case class WhaleSighting(
                          timestamp:   Timestamp,
                          latitude:    Double,
                          longitude:   Double,
                          size:        Option[WhaleSize]  = None,
                          colors:      Seq[WhaleColor]    = Seq.empty,
                          nostrils:    Option[Int]        = None,
                          spots:       Option[Boolean]    = None,
                          teeth:       Option[Boolean]    = None,
                          depth:       Option[Int]        = Some(0),
                          whaleType:   Option[WhaleType]  = None
                         )
