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
  .settings(
    name := "common",
    libraryDependencies ++= Seq(Dependencies.scalaHttpJ)
  )
  .disablePlugins(AssemblyPlugin)

lazy val consumer = project
  .settings(
    name := "consumer",
    mainClass in (Compile, run) := Some("com.example.spark.Main"),
    assemblySettings,
    libraryDependencies ++= commonDependencies
  )
  .dependsOn(
    common
  )

lazy val producer = project
  .settings(
    name := "producer",
    assemblySettings,
    libraryDependencies ++= commonDependencies
  )
  .dependsOn(
    common
  )

lazy val commonDependencies = Seq(
  Dependencies.logback,
  Dependencies.scalaLogging,
  Dependencies.slf4j,
  Dependencies.awsS3 % Compile,
  Dependencies.hadoopAws % Compile,
  Dependencies.spark % Compile,
  Dependencies.sparkSql % Compile,
  Dependencies.scalaTest % Test
)

lazy val assemblySettings = Seq(
  assemblyJarName in assembly := name.value + ".jar",
  assemblyMergeStrategy in assembly := {
    case PathList("META-INF", xs @ _*) => MergeStrategy.discard
    case "application.conf"            => MergeStrategy.concat
    case x =>
      val oldStrategy = (assemblyMergeStrategy in assembly).value
      oldStrategy(x)
  }
)
