import sbt._

object Dependencies {
  val akkaV         = "2.5.25"
  val awsV          = "1.11.524"
  val circeV        = "0.11.1"
  val hadoopAwsV    = "2.8.2"
  val logbackV      = "1.2.3"
  val scalaHttpJV   = "2.4.2"
  val scalaLoggingV = "3.7.2"
  val scalaTestV    = "3.0.8"
  val slf4jV        = "1.7.25"
  val sparkV        = "2.4.4"

  lazy val akkaActor      = "com.typesafe.akka"           %%  "akka-actor"        % akkaV
  lazy val awsCore        = "com.amazonaws"               %   "aws-java-sdk-core" % awsV
  lazy val awsS3          = "com.amazonaws"               %   "aws-java-sdk-s3"   % awsV
  lazy val awsSdk         = "com.amazonaws"               %   "aws-java-sdk"      % awsV
  lazy val circeCore      = "io.circe"                    %%  "circe-core"        % circeV
  lazy val circeGeneric   = "io.circe"                    %%  "circe-generic"     % circeV
  lazy val circeParser    = "io.circe"                    %%  "circe-parser"      % circeV
  lazy val hadoopAws      = "org.apache.hadoop"           %   "hadoop-aws"        % hadoopAwsV
  lazy val logback        = "ch.qos.logback"              %   "logback-classic"   % logbackV
  lazy val scalaLogging   = "com.typesafe.scala-logging"  %%  "scala-logging"     % scalaLoggingV
  lazy val scalaHttpJ     = "org.scalaj"                  %%  "scalaj-http"       % scalaHttpJV
  lazy val scalaTest      = "org.scalatest"               %%  "scalatest"         % scalaTestV
  lazy val slf4j          = "org.slf4j"                   %   "jcl-over-slf4j"    % slf4jV
  lazy val sparkStreaming = "org.apache.spark"            %%  "spark-streaming"   % sparkV
  lazy val sparkSql       = "org.apache.spark"            %%  "spark-sql"         % sparkV
}
