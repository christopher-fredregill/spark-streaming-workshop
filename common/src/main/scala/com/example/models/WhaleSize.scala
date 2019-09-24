package com.example.models

/**
  * Models sizes of whales which can be found in the wild; small, medium, or large.
  * @author christopher
  * @since 2019-09-19
  */
sealed trait WhaleSize {
  val label: String
}

case object Small   extends WhaleSize {
  override val label: String = "Small"
}

case object Medium  extends WhaleSize {
  override val label: String = "Medium"
}

case object Large   extends WhaleSize {
  override val label: String = "Large"
}
