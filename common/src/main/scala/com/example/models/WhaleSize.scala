package com.example.models

/**
  * Models sizes of whales which can be found in the wild; small, medium, or large.
  * @author christopher
  * @since 2019-09-19
  */
sealed trait WhaleSize
case object Small   extends WhaleSize
case object Medium  extends WhaleSize
case object Large   extends WhaleSize
