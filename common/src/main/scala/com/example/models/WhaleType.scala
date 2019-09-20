package com.example.models

/**
  * Types of whales which can be sighted in the wild.
  * @author christopher
  * @since 2019-09-19
  */
sealed trait WhaleType {
  val name: String
  val colors: Seq[WhaleColor]
  val size: WhaleSize
  val tusk: Boolean = false
  val spots: Boolean = false
  val teeth: Boolean
  val nostrils: Int
  val dorsalFin: Boolean
}

sealed trait BaleenWhale extends WhaleType {
  override val nostrils = 2
  override val teeth = false
}

sealed trait ToothedWhale extends WhaleType {
  override val nostrils = 1
  override val teeth = true
}

sealed trait OceanicDolphin extends ToothedWhale {
  override val dorsalFin: Boolean = true
}

case object GrayWhale extends BaleenWhale {
  override val name: String = "Gray Whale"
  override val colors: Seq[WhaleColor] = Seq(DarkGray)
  override val size: WhaleSize = Medium
  override val dorsalFin: Boolean = false
  override val spots: Boolean = true
}

case object BlueWhale extends BaleenWhale {
  override val name: String = "Blue Whale"
  override val colors: Seq[WhaleColor] = Seq(Blue)
  override val size: WhaleSize = Large
  override val dorsalFin: Boolean = true
}

case object HumpbackWhale extends BaleenWhale {
  override val name: String = "Humpback Whale"
  override val colors: Seq[WhaleColor] = Seq(Black)
  override val size: WhaleSize = Large
  override val dorsalFin: Boolean = true
}

case object FinWhale extends BaleenWhale {
  override val name: String = "Fin Whale"
  override val colors: Seq[WhaleColor] = Seq(Brown, LightGray, White)
  override val size: WhaleSize = Large
  override val dorsalFin: Boolean = true
}

case object SeiWhale extends BaleenWhale {
  override val name: String = "Sei Whale"
  override val colors: Seq[WhaleColor] = Seq(DarkGray, LightGray, White)
  override val size: WhaleSize = Large
  override val dorsalFin: Boolean = true
}

case object BrydesWhale extends BaleenWhale {
  override val name: String = "Bryde's Whale"
  override val colors: Seq[WhaleColor] = Seq(DarkGray, White)
  override val size: WhaleSize = Medium
  override val dorsalFin: Boolean = true
}

case object MinkeWhale extends BaleenWhale {
  override val name: String = "Minke Whale"
  override val colors: Seq[WhaleColor] = Seq(Black, DarkGray)
  override val size: WhaleSize = Small
  override val dorsalFin: Boolean = true
}

case object RightWhale extends BaleenWhale {
  override val name: String = "Right Whale"
  override val colors: Seq[WhaleColor] = Seq(Black, DarkGray)
  override val size: WhaleSize = Large
  override val dorsalFin: Boolean = false
}

case object SpermWhale extends ToothedWhale {
  override val name: String = "Sperm Whale"
  override val colors: Seq[WhaleColor] = Seq(DarkGray)
  override val size: WhaleSize = Large
  override val dorsalFin: Boolean = true
}

case object Narwhal extends ToothedWhale {
  override val name: String = "Narwhal"
  override val colors: Seq[WhaleColor] = Seq(White)
  override val size: WhaleSize = Medium
  override val tusk: Boolean = true
  override val dorsalFin: Boolean = false
}

case object Orca extends OceanicDolphin {
  override val name: String = "Orca"
  override val colors: Seq[WhaleColor] = Seq(Black, White)
  override val size: WhaleSize = Medium
  override val dorsalFin: Boolean = true
}

case object FalseKillerWhale extends OceanicDolphin {
  override val name: String = "False Killer Whale"
  override val colors: Seq[WhaleColor] = Seq(Black, DarkGray)
  override val size: WhaleSize = Medium
}

case object Beluga extends ToothedWhale {
  override val name: String = "Beluga Whale"
  override val colors: Seq[WhaleColor] = Seq(White)
  override val size: WhaleSize = Medium
  override val dorsalFin: Boolean = false
}

case object BottlenoseDolphin extends OceanicDolphin {
  override val name: String = "Bottlenose Dolphin"
  override val colors: Seq[WhaleColor] = Seq(LightGray)
  override val size: WhaleSize = Small
}

case object CommonDolphin extends OceanicDolphin {
  override val name: String = "Common Dolphin"
  override val colors: Seq[WhaleColor] = Seq(DarkGray, White)
  override val size: WhaleSize = Small
}

case object SpottedDolphin extends OceanicDolphin {
  override val name: String = "Spotted Dolphin"
  override val colors: Seq[WhaleColor] = Seq(White, LightGray)
  override val size: WhaleSize = Small
  override val spots: Boolean = true
}

case object DuskyDolphin extends OceanicDolphin {
  override val name: String = "Dusky Dolphin"
  override val colors: Seq[WhaleColor] = Seq(DarkGray, White)
  override val size: WhaleSize = Small
}
