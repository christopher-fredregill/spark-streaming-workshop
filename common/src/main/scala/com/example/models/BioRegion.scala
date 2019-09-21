package com.example.models

import scala.language.implicitConversions
import scala.util.Random

/**
  * Bio-regions in which [[WhaleSighting]]s can occur.
  *
  * @author christopher
  * @since 2019-09-21
  */
sealed trait BioRegion {
  /** A [[Seq]] of spawn points as latitude/longitude pairs. */
  val locations: Seq[(Double, Double)]

  /** The greatest distance away from a fixed spawn point about which a [[WhaleSighting]] can occur. */
  val radius: Double

  /** Simulate a location within the region. */
  def simulateLocation(random: Random): (Double, Double) = random.shuffle(locations).head
}

sealed trait CoastalBioRegion extends BioRegion {
  override val radius: Double = 1
}

sealed trait OpenOceanBioRegion extends BioRegion {
  override val radius: Double = 100.0
}

case object NorthPacificCoast extends CoastalBioRegion {

  override val locations: Seq[(Double, Double)] = Seq(
    (32.576499, -117.189768),
    (32.666489, -117.194407),
    (32.678128, -117.278432),
    (32.939794, -117.333492),
    (33.210535, -117.573004),
    (33.621015, -118.437441),
    (33.840858, -120.027882),
    (34.358136, -120.744440),
    (34.671237, -120.828856),
    (35.601281, -121.375957),
    (36.710498, -122.010961),
    (37.003531, -122.347919),
    (38.370943, -123.290593),
    (38.655462, -123.677498),
    (39.311436, -123.899315),
    (41.534352, -124.633546),
    (42.164775, -124.767609),
    (43.385193, -124.702644),
    (44.817224, -124.659102),
    (48.299469, -122.920143),
    (48.598334, -126.164054),
    (49.309011, -123.970430),
    (51.745738, -128.874171),
    (58.588359, -139.409465),
    (59.510121, -143.371935),
    (58.579152, -150.100974),
    (56.100361, -157.585523),
    (54.374482, -161.235197),
    (57.947175, -163.787034)
  )
}

case object SouthPacificCoast extends CoastalBioRegion {
  override val locations: Seq[(Double, Double)] = Seq(
    (-48.974771, -79.823554),
    (-54.962138, -77.699827),
    (-44.144782, -76.999871),
    (-40.698895, -76.001487),
    (-37.958543, -74.799165),
    (-34.991673, -73.573782),
    (-30.417877, -73.458251),
    (-27.590465, -72.662543),
    (-23.594762, -72.448277),
    (-19.105572, -72.009616),
    (-15.669688, -75.923782),
    (-11.573876, -79.379993),
    (-7.391933, -81.453504),
    (-3.945054, -82.559982),
    (-0.421232, -92.931879)
  )
}

case object Hawaii extends CoastalBioRegion {
  override val locations: Seq[(Double, Double)] = Seq(
    (19.476880, -158.231651),
    (20.757954, -160.472173),
    (22.255062, -161.262470),
    (20.242222, -154.653407),
    (22.174205, -157.619935),
    (21.163097, -157.888835),
    (24.171397, -157.563919),
    (17.970324, -153.134200),
    (21.780585, -166.744821)
  )
}

case object MexicoPacific extends CoastalBioRegion {
  override val locations: Seq[(Double, Double)] = Seq(
    (30.994001, -114.099743),
    (29.749338, -113.091678),
    (28.307053, -112.216509),
    (26.901719, -111.310117),
    (25.244009, -110.074100),
    (23.689101, -108.321466),
    (22.582990, -107.963988),
    (23.095814, -111.509616),
    (25.472940, -113.914371),
    (28.173399, -114.695989),
    (29.873290, -116.360027),
    (31.545033, -117.844258),
    (21.156542, -107.011921),
    (19.199228, -106.477678),
    (17.441717, -104.254949),
    (15.958834, -100.365958),
    (14.772167, -96.522478),
    (13.561072, -93.225387)
  )
}

case object GulfOfMexico extends CoastalBioRegion {
  override val locations: Seq[(Double, Double)] = Seq(
    (23.695497, -96.321828),
    (20.401384, -95.425821),
    (20.019693, -91.920537),
    (22.489990, -88.794825),
    (19.549001, -85.923642),
    (27.691804, -88.829196),
    (25.293866, -84.092825),
    (24.799873, -91.830894)
  )
}

case object Caribbean extends CoastalBioRegion {
  override val locations: Seq[(Double, Double)] = Seq(
    (17.774723, -84.432326),
    (16.035139, -80.787064),
    (12.301704, -78.220942),
    (14.443990, -72.368365),
    (14.136128, -64.512064)
  )
}

case object NorthAtlanticCoast extends CoastalBioRegion {
  override val locations: Seq[(Double, Double)] = Seq(
    (27.766999, -79.122655),
    (30.363462, -78.358026),
    (32.443707, -77.790829),
    (34.083096, -74.155775),
    (37.626192, -72.501325),
    (40.988059, -67.959323),
    (42.747008, -62.266066),
    (45.096376, -57.524720),
    (22.886713, -76.005706),
    (23.279100, -72.357929),
    (21.868281, -69.355314),
    (19.434809, -64.252437),
    (17.742423, -60.391649),
    (13.981624, -59.373023),
    (10.427520, -58.999794),
    (9.116614, -54.509557),
    (6.386732, -47.759387),
    (1.705970, -43.069242)
  )
}

case object SouthAtlanticCoast extends CoastalBioRegion {
  override val locations: Seq[(Double, Double)] = Seq(
    (-1.977705, -35.371606),
    (-8.948542, -30.015138),
    (-14.457841, -31.865995),
    (-18.351164, -34.909460),
    (-21.049541, -37.077340),
    (-23.861888, -40.185657),
    (-23.416860, -42.766244),
    (-24.211707, -44.686085),
    (-24.572611, -45.877216),
    (-25.349805, -46.597464),
    (-26.388393, -47.237777),
    (-28.188322, -47.326598),
    (-29.625914, -48.579158),
    (-31.429763, -49.519222),
    (-33.265519, -51.101662),
    (-34.706322, -52.699435),
    (-35.544153, -56.149617),
    (-36.914077, -55.660777),
    (-38.711834, -55.944685),
    (-39.689733, -57.844653),
    (-41.343319, -59.991273),
    (-43.234854, -61.829421),
    (-45.966525, -63.924458),
    (-49.290437, -63.742179),
    (-54.003141, -63.294790),
    (-49.290231, -57.222082),
    (-52.062724, -55.644330)
  )
}

case object NorthPacificOcean extends OpenOceanBioRegion {
  override val locations: Seq[(Double, Double)] = Seq(
    (30.654668, -128.381864),
    (19.055885, -121.250409),
    (32.704816, -154.298180),
    (44.691642, -151.896890),
    (36.635845, -174.736977),
    (16.152308, -173.593725),
    (8.070925, -131.214702),
    (1.728367, -152.654035)
  )
}

case object SouthPacificOcean extends OpenOceanBioRegion {
  override val locations: Seq[(Double, Double)] = Seq(
    (-11.153367, -139.602251),
    (-26.303112, -140.605788),
    (-28.955649, -113.037733),
    (-31.257354, -91.996860),
    (-17.136346, -95.464439),
    (-11.609385, -96.280910),
    (-30.321381, -138.707897)
  )
}

case object SouthAtlanticOcean extends OpenOceanBioRegion {
  override val locations: Seq[(Double, Double)] = Seq(
    (-44.445491, -31.947229),
    (-26.972103, -23.031111),
    (-16.848419, -16.033136),
    (-36.060536, -8.640810),
    (-52.223433, -13.637975)
  )
}

case object NorthAtlanticOcean extends OpenOceanBioRegion {
  override val locations: Seq[(Double, Double)] = Seq(
    (34.609075, -60.529745),
    (40.955079, -46.605777),
    (28.580545, -39.119531),
    (17.024413, -49.846787),
    (12.187116, -32.554970),
    (49.483909, -27.793626),
    (39.080045, -42.968971),
    (58.714241, -55.526351),
    (69.664116, -58.812020)
  )
}

case object ArcticOcean extends OpenOceanBioRegion {
  override val locations: Seq[(Double, Double)] = Seq(
    (76.321381, -142.024072),
    (73.593042, -168.989702),
    (71.225230, -128.801483),
    (69.401331, -165.561840),
    (75.864226, -72.820042),
    (82.817555, 12.012081),
    (87.862247, -54.574540),
    (56.673934, -85.989167),
    (56.250666, -82.098045),
    (60.387788, -92.981397),
    (62.226117, -86.280390),
    (66.025009, -79.914797)
  )
}