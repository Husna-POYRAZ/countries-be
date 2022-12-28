#
# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS MAVEN_BUILD

WORKDIR /build/

COPY pom.xml /build/
COPY src /build/src/


RUN mvn package

#
# Package stage 
#
FROM openjdk:11-jre-slim

WORKDIR /app

ARG JAR_FILE=/build/target/*.jar

COPY --from=MAVEN_BUILD ${JAR_FILE} /app/countries-be.jar
COPY asset /app/asset/

EXPOSE 8080

ENTRYPOINT ["java","-jar","countries-be.jar"]


