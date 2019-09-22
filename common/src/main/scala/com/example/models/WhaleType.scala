package com.example.models

import scala.util.Random

/**
  * Types of whales which can be sighted in the wild.
  *
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

  def simulateRegion(random: Random): BioRegion
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

// 26,000
case object GrayWhale extends BaleenWhale {
  override val name: String = "Gray Whale"
  override val colors: Seq[WhaleColor] = Seq(DarkGray)
  override val size: WhaleSize = Medium
  override val dorsalFin: Boolean = false
  override val spots: Boolean = true

  override def simulateRegion(random: Random): BioRegion = {
    random.nextDouble() match {
      case u: Double if u < 0.45 => MexicoPacific
      case u: Double if u < 0.85 => NorthPacificCoast
      case u: Double if u < 0.95 => ArcticOcean
      case _                     => NorthPacificOcean
    }
  }
}

// 25,000
case object BlueWhale extends BaleenWhale {
  override val name: String = "Blue Whale"
  override val colors: Seq[WhaleColor] = Seq(Blue)
  override val size: WhaleSize = Large
  override val dorsalFin: Boolean = true

  override def simulateRegion(random: Random): BioRegion = {
    random.nextDouble() match {
      case u: Double if u < 0.1 => NorthPacificCoast
      case u: Double if u < 0.2 => NorthPacificOcean
      case u: Double if u < 0.3 => MexicoPacific
      case u: Double if u < 0.5 => Hawaii
      case u: Double if u < 0.6 => Caribbean
      case u: Double if u < 0.7 => NorthAtlanticCoast
      case u: Double if u < 0.8 => NorthAtlanticOcean
      case u: Double if u < 0.9 => SouthAtlanticCoast
      case _                    => SouthAtlanticOcean
    }
  }
}

// 80,000
case object HumpbackWhale extends BaleenWhale {
  override val name: String = "Humpback Whale"
  override val colors: Seq[WhaleColor] = Seq(Black)
  override val size: WhaleSize = Large
  override val dorsalFin: Boolean = true

  override def simulateRegion(random: Random): BioRegion = {
    random.nextDouble() match {
      case u: Double if u < 0.2 => NorthPacificCoast
      case u: Double if u < 0.4 => MexicoPacific
      case u: Double if u < 0.5 => Hawaii
      case u: Double if u < 0.6 => Caribbean
      case u: Double if u < 0.7 => SouthAtlanticCoast
      case u: Double if u < 0.8 => NorthAtlanticCoast
      case _                    => ArcticOcean
    }
  }
}

// 46,000
case object FinWhale extends BaleenWhale {
  override val name: String = "Fin Whale"
  override val colors: Seq[WhaleColor] = Seq(Brown, LightGray, White)
  override val size: WhaleSize = Large
  override val dorsalFin: Boolean = true

  override def simulateRegion(random: Random): BioRegion = {
    random.nextDouble() match {
      case u: Double if u < 0.1  => NorthPacificCoast
      case u: Double if u < 0.25 => NorthPacificOcean
      case u: Double if u < 0.33 => Hawaii
      case u: Double if u < 0.45 => SouthAtlanticCoast
      case u: Double if u < 0.5  => SouthAtlanticOcean
      case u: Double if u < 0.6  => NorthAtlanticCoast
      case u: Double if u < 0.7  => NorthAtlanticCoast
      case _                     => ArcticOcean
    }
  }
}

// 80,000
case object SeiWhale extends BaleenWhale {
  override val name: String = "Sei Whale"
  override val colors: Seq[WhaleColor] = Seq(DarkGray, LightGray, White)
  override val size: WhaleSize = Large
  override val dorsalFin: Boolean = true

  override def simulateRegion(random: Random): BioRegion = {
    random.nextDouble() match {
      case u: Double if u < 0.11 => NorthPacificCoast
      case u: Double if u < 0.22 => NorthPacificOcean
      case u: Double if u < 0.33 => SouthPacificCoast
      case u: Double if u < 0.44 => SouthPacificOcean
      case u: Double if u < 0.55 => NorthAtlanticCoast
      case u: Double if u < 0.66 => NorthAtlanticOcean
      case u: Double if u < 0.77 => SouthAtlanticOcean
      case u: Double if u < 0.88 => SouthAtlanticCoast
      case _                     => Hawaii
    }
  }
}

// 100,000
case object BrydesWhale extends BaleenWhale {
  override val name: String = "Bryde's Whale"
  override val colors: Seq[WhaleColor] = Seq(DarkGray, White)
  override val size: WhaleSize = Medium
  override val dorsalFin: Boolean = true

  override def simulateRegion(random: Random): BioRegion = {
    random.nextDouble() match {
      case u: Double if u < 0.25 => MexicoPacific
      case u: Double if u < 0.5  => SouthPacificOcean
      case u: Double if u < 0.6  => SouthPacificCoast
      case u: Double if u < 0.75 => SouthAtlanticOcean
      case u: Double if u < 0.85 => SouthAtlanticCoast
      case _                     => Caribbean
    }
  }
}

// 515,000
case object MinkeWhale extends BaleenWhale {
  override val name: String = "Minke Whale"
  override val colors: Seq[WhaleColor] = Seq(Black, DarkGray)
  override val size: WhaleSize = Small
  override val dorsalFin: Boolean = true

  override def simulateRegion(random: Random): BioRegion = {
    random.nextDouble() match {
      case u: Double if u < 0.13 => NorthPacificCoast
      case u: Double if u < 0.25 => NorthPacificOcean
      case u: Double if u < 0.36 => SouthPacificCoast
      case u: Double if u < 0.48 => SouthPacificOcean
      case u: Double if u < 0.66 => NorthAtlanticCoast
      case u: Double if u < 0.77 => NorthAtlanticOcean
      case u: Double if u < 0.82 => SouthAtlanticCoast
      case u: Double if u < 0.85 => SouthAtlanticOcean
      case u: Double if u < 0.93 => Caribbean
      case _                     => GulfOfMexico
    }
  }
}

// 15,000
case object RightWhale extends BaleenWhale {
  override val name: String = "Right Whale"
  override val colors: Seq[WhaleColor] = Seq(Black, DarkGray)
  override val size: WhaleSize = Large
  override val dorsalFin: Boolean = false
  override val spots: Boolean = true

  override def simulateRegion(random: Random): BioRegion = {
    random.nextDouble() match {
      case u: Double if u < 0.2  => NorthAtlanticOcean
      case u: Double if u < 0.25 => NorthAtlanticCoast
      case u: Double if u < 0.4  => SouthAtlanticOcean
      case u: Double if u < 0.45 => SouthAtlanticCoast
      case u: Double if u < 0.6  => SouthPacificOcean
      case u: Double if u < 0.65 => SouthPacificCoast
      case u: Double if u < 0.8  => NorthPacificOcean
      case _                     => NorthPacificCoast
    }
  }
}

// 360,000
case object SpermWhale extends ToothedWhale {
  override val name: String = "Sperm Whale"
  override val colors: Seq[WhaleColor] = Seq(DarkGray)
  override val size: WhaleSize = Large
  override val dorsalFin: Boolean = true

  override def simulateRegion(random: Random): BioRegion = {
    random.nextDouble() match {
      case u: Double if u < 0.1  => Hawaii
      case u: Double if u < 0.25 => GulfOfMexico
      case u: Double if u < 0.35 => Caribbean
      case u: Double if u < 0.4  => NorthAtlanticOcean
      case u: Double if u < 0.55 => NorthAtlanticCoast
      case u: Double if u < 0.6  => SouthAtlanticOcean
      case u: Double if u < 0.75 => SouthAtlanticCoast
      case u: Double if u < 0.8  => SouthPacificOcean
      case u: Double if u < 0.85 => SouthPacificCoast
      case u: Double if u < 0.9  => NorthPacificOcean
      case _                     => NorthPacificCoast
    }
  }
}

// 170,000
case object Narwhal extends ToothedWhale {
  override val name: String = "Narwhal"
  override val colors: Seq[WhaleColor] = Seq(White)
  override val size: WhaleSize = Medium
  override val tusk: Boolean = true
  override val dorsalFin: Boolean = false

  override def simulateRegion(random: Random): BioRegion = {
    random.nextDouble() match {
      case u: Double if u < 0.33 => NorthAtlanticOcean
      case u: Double if u < 0.66 => NorthAtlanticCoast
      case _                     => ArcticOcean
    }
  }
}

// 50,000
case object Orca extends OceanicDolphin {
  override val name: String = "Orca"
  override val colors: Seq[WhaleColor] = Seq(Black, White)
  override val size: WhaleSize = Medium
  override val dorsalFin: Boolean = true

  override def simulateRegion(random: Random): BioRegion = {
    random.nextDouble() match {
      case u: Double if u < 0.1  => Hawaii
      case u: Double if u < 0.25 => GulfOfMexico
      case u: Double if u < 0.35 => Caribbean
      case u: Double if u < 0.4  => NorthAtlanticOcean
      case u: Double if u < 0.55 => NorthAtlanticCoast
      case u: Double if u < 0.6  => SouthAtlanticOcean
      case u: Double if u < 0.75 => SouthAtlanticCoast
      case u: Double if u < 0.8  => SouthPacificOcean
      case u: Double if u < 0.85 => SouthPacificCoast
      case u: Double if u < 0.9  => NorthPacificOcean
      case u: Double if u < 0.95 => NorthPacificCoast
      case _                     => ArcticOcean
    }
  }
}

// 26,000
case object FalseKillerWhale extends OceanicDolphin {
  override val name: String = "False Killer Whale"
  override val colors: Seq[WhaleColor] = Seq(Black, DarkGray)
  override val size: WhaleSize = Medium

  override def simulateRegion(random: Random): BioRegion = {
    random.nextDouble() match {
      case u: Double if u < 0.2 => SouthAtlanticCoast
      case u: Double if u < 0.3 => SouthPacificOcean
      case u: Double if u < 0.4 => SouthPacificCoast
      case u: Double if u < 0.5 => NorthPacificOcean
      case u: Double if u < 0.6 => NorthPacificCoast
      case u: Double if u < 0.7 => GulfOfMexico
      case u: Double if u < 0.8 => Caribbean
      case _                    => NorthAtlanticOcean
    }
  }
}

// 150,000
case object Beluga extends ToothedWhale {
  override val name: String = "Beluga Whale"
  override val colors: Seq[WhaleColor] = Seq(White)
  override val size: WhaleSize = Medium
  override val dorsalFin: Boolean = false

  override def simulateRegion(random: Random): BioRegion = {
    random.nextDouble() match {
      case u: Double if u < 0.01 => NorthAtlanticCoast
      case u: Double if u < 0.03 => NorthAtlanticOcean
      case u: Double if u < 0.06 => NorthPacificOcean
      case u: Double if u < 0.07 => NorthPacificCoast
      case _                     => ArcticOcean
    }
  }
}

// 600,000
case object BottlenoseDolphin extends OceanicDolphin {
  override val name: String = "Bottlenose Dolphin"
  override val colors: Seq[WhaleColor] = Seq(LightGray)
  override val size: WhaleSize = Small

  override def simulateRegion(random: Random): BioRegion = {
    random.nextDouble() match {
      case u: Double if u < 0.1  => NorthPacificOcean
      case u: Double if u < 0.2  => NorthPacificCoast
      case u: Double if u < 0.25 => MexicoPacific
      case u: Double if u < 0.3  => SouthPacificOcean
      case u: Double if u < 0.4  => SouthPacificCoast
      case u: Double if u < 0.5  => SouthAtlanticOcean
      case u: Double if u < 0.6  => SouthAtlanticCoast
      case u: Double if u < 0.65 => NorthAtlanticOcean
      case u: Double if u < 0.7  => NorthAtlanticCoast
      case u: Double if u < 0.8  => GulfOfMexico
      case u: Double if u < 0.9  => Hawaii
      case _                     => Caribbean
    }
  }
}

// 1,000,000
case object CommonDolphin extends OceanicDolphin {
  override val name: String = "Common Dolphin"
  override val colors: Seq[WhaleColor] = Seq(DarkGray, White)
  override val size: WhaleSize = Small

  override def simulateRegion(random: Random): BioRegion = {
    random.nextDouble() match {
      case u: Double if u < 0.15 => NorthPacificCoast
      case u: Double if u < 0.25 => MexicoPacific
      case u: Double if u < 0.35 => SouthPacificCoast
      case u: Double if u < 0.45 => SouthAtlanticCoast
      case u: Double if u < 0.65 => NorthAtlanticCoast
      case u: Double if u < 0.75 => GulfOfMexico
      case u: Double if u < 0.85 => Hawaii
      case _                     => Caribbean
    }
  }
}

// 81,000
case object SpottedDolphin extends OceanicDolphin {
  override val name: String = "Spotted Dolphin"
  override val colors: Seq[WhaleColor] = Seq(White, LightGray)
  override val size: WhaleSize = Small
  override val spots: Boolean = true

  override def simulateRegion(random: Random): BioRegion = {
    random.nextDouble() match {
      case u: Double if u < 0.15 => NorthAtlanticCoast
      case u: Double if u < 0.3  => NorthAtlanticOcean
      case u: Double if u < 0.5  => GulfOfMexico
      case u: Double if u < 0.6  => SouthAtlanticCoast
      case u: Double if u < 0.75 => SouthAtlanticOcean
      case _                     => Caribbean
    }
  }
}

// 70,000
case object DuskyDolphin extends OceanicDolphin {
  override val name: String = "Dusky Dolphin"
  override val colors: Seq[WhaleColor] = Seq(DarkGray, White)
  override val size: WhaleSize = Small

  override def simulateRegion(random: Random): BioRegion = {
    random.nextDouble() match {
      case u: Double if u < 0.05 => SouthAtlanticCoast
      case u: Double if u < 0.95 => SouthPacificCoast
      case _                     => SouthPacificOcean
    }
  }
}
