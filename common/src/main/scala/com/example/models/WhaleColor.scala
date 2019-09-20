package com.example.models

/**
  * Discrete colors of animals reported in the wild.
  * @author christopher
  * @since 2019-09-19
  */
sealed trait WhaleColor {
  val label: String
}

case object Red extends WhaleColor {
  override val label: String = "Red"
}

case object Brown extends WhaleColor {
  override val label: String = "Brown"
}

case object Blue extends WhaleColor {
  override val label: String = "Blue"
}

case object Black extends WhaleColor {
  override val label: String = "Black"
}

case object DarkGray extends WhaleColor {
  override val label: String = "Dark Gray"
}

case object LightGray extends WhaleColor {
  override val label: String = "Light Gray"
}

case object White extends WhaleColor {
  override val label: String = "White"
}
