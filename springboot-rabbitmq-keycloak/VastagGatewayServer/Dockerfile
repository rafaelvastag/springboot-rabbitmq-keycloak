FROM maven:3.8.6-openjdk-11 as build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTest

FROM openjdk:11
WORKDIR /app
COPY --from=build ./app/target/*.jar ./app.jar

ARG EUREKA_SERVER=localhost
ARG EUREKA_SERVER_PORT=8761
ARG KEYCLOAK_SERVER=localhost
ARG KEYCLOAK_SERVER_PORT=8080

EXPOSE 8080
ENTRYPOINT java -jar app.jar