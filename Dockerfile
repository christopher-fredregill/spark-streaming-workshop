FROM mozilla/sbt:8u212_1.2.8 as builder

WORKDIR /app

COPY build.sbt  /app/
COPY project    /app/project
COPY common     /app/common
COPY producer   /app/producer

RUN sbt producer:assembly
RUN chmod +x producer/target/scala-2.12/producer.jar

FROM openjdk:8-slim-stretch

WORKDIR /srv
COPY --from=builder /app/producer/target/scala-2.12/producer.jar /srv
