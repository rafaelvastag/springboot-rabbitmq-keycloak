FROM maven:3.8.6-openjdk-11 as build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTest

FROM openjdk:11
WORKDIR /app
COPY --from=build ./app/target/*.jar ./app.jar

ARG RABBITMQ_SERVER=rabbitmq
ARG RABBITMQ_SERVER_PORT=5672
ARG EUREKA_SERVER=eureka
ARG EUREKA_SERVER_PORT=8761

ENTRYPOINT java -jar app.jar