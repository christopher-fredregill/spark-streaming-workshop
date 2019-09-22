ThisBuild / scalaVersion     := "2.12.0"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "example"

// Projects

lazy val global = project
  .in(file("."))
  .aggregate(
    common,
    producer,
    consumer
  )

lazy val common = project
  .disablePlugins(sbtassembly.AssemblyPlugin)
  .settings(
    name := "common",
    libraryDependencies ++= Seq(Dependencies.scalaHttpJ)
  )
  .disablePlugins(AssemblyPlugin)

lazy val consumer = project
  .settings(
    name := "consumer",
    mainClass in (Compile, run) := Some("com.example.spark.ConsumerMain"),
    assemblySettings,
    libraryDependencies ++= commonDependencies ++ sparkDependencies,
    libraryDependencies += Dependencies.awsS3 % Compile
  )
  .dependsOn(
    common
  )

lazy val producer = project
  .settings(
    name := "producer",
    mainClass in (Compile, run) := Some("com.example.emitter.ProducerMain"),
    assemblySettings,
    libraryDependencies ++= commonDependencies,
    libraryDependencies += Dependencies.awsS3 % Compile,
    libraryDependencies += Dependencies.akkaActor % Compile,
    libraryDependencies ++= Seq(
      Dependencies.circeCore,
      Dependencies.circeGeneric,
      Dependencies.circeParser
    ).map(_ % Compile)
  )
  .dependsOn(
    common
  )

lazy val sparkDependencies = Seq(
  Dependencies.hadoopAws % Compile,
  Dependencies.spark % Compile,
  Dependencies.sparkSql % Compile
)

lazy val commonDependencies = Seq(
  Dependencies.logback,
  Dependencies.scalaLogging,
  Dependencies.slf4j,
  Dependencies.scalaTest % Test
)

lazy val assemblySettings = Seq(
  assemblyJarName in assembly := name.value + ".jar",
  assemblyMergeStrategy in assembly := {
    case PathList("META-INF", xs @ _*) => MergeStrategy.discard
    case "application.conf"            => MergeStrategy.concat
    case _                             => MergeStrategy.first
  }
)
