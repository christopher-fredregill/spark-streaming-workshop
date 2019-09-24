# Spark Streaming Workshop

## Pre-Requisites

The demo is known to work on Mac OSX, running with the below dependencies.

### Java
I happen to be using OpenJDK version 1.8.0_222 (https://adoptopenjdk.net/index.html); other versions of Java 1.8 probably work.

### Scala
Scala 2.12.0 (https://www.scala-lang.org/download/2.12.0.html)

## SBT

The project uses sbt version 1.3.0.
See https://www.scala-sbt.org/release/docs/Installing-sbt-on-Mac.html if you need to install it.

### Docker
Docker version 19.03.2

## Minio

We'll be using [minio](https://min.io/) as a local stand-in for S3. The [docker-compose](docker-compose.yml) file in the project root contains a service definition which will allow you to spin up a local `minio` container. From the project root, run the following:
```bash
docker-compose up -d
```

If all is working, you should be able to nav to [http://localhost:9000](http://localhost:9000) and see a bucket called "example-bucket."

## Simulating Whale Sightings

The demo relies on simulated whale sighting event data which is periodically sent to an S3 path. The event emitter can also be run via `docker-compose`:

```bash
docker-compose up -d sighting_producer
```

If all is working, you should start to see log messages with locations and timestamps of whale sightings.

## Extra Configs

The following environment variables in the [.env](.env) file can be used to configure the frequency and content of events emitted:

```bash
# How frequently to emit events
INTERVAL_MILLIS=1000

# Optionally, simulate the arrival of "late" events that are delayed by the given number of milliseconds
#TIMESTAMP_MILLIS_DELAYED=

# Optionally, set to "true" to include the type of whale in the sighting event
#REPORT_CLASSIFICATION=true
```

## Running the Streaming App

Once you have a backing S3 store (`minio`) and event emitter up and running, start a new sbt shell session:

```bash
$ sbt
sbt:global>
```

Once in the sbt shell, run `project consumer` to switch to the `consumer` project:  

```bash
sbt:global> project consumer
[info] Set current project to consumer (in build file:~/spark-streaming-workshop/) 
```

From there, you should be able to run `run` to get the `consumer` streaming app running:
```bash
sbt:consumer> run
[info] Compiling 1 Scala source to /Users/christopher/Projects/Meetups/sb_big_data/spark-streaming-workshop/common/target/scala-2.12/classes ...
[info] Compiling 1 Scala source to /Users/christopher/Projects/Meetups/sb_big_data/spark-streaming-workshop/consumer/target/scala-2.12/classes ...
[warn] there was one deprecation warning (since 2.11.0); re-run with -deprecation for details
[warn] one warning found
[info] running com.example.spark.ConsumerMain
...
```

From here, you can use `Ctrl+C` to stop the app and make updates to the streaming query in [ConsumerMain](com.example.spark.ConsumerMain). You can re-start the app again at any time by running `run` from your sbt shell.
