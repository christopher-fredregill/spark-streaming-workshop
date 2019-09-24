# Spark Streaming Workshop

## Pre-Requisites

### Java
OpenJDK version 1.8.0_222 (https://adoptopenjdk.net/index.html)

### Scala
Scala 2.12.0 (https://www.scala-lang.org/download/2.12.0.html)

### Docker
Docker version 19.03.2

## Minio

We'll be using [minio](https://min.io/) as a local stand-in for S3. The [docker-compose](docker-compose.yml) file in the project root contains a service definition which will allow you to spin up a local `minio` container. From the project root, run the following:
```bash
docker-compose up -d
```
