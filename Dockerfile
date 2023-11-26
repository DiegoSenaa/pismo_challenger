FROM maven:3.9.5-amazoncorretto-21-al2023 AS base
ENV TZ=America/Sao_Paulo

COPY src /home/app/src
COPY pom.xml /home/app

EXPOSE 8080

RUN mvn -f /home/app/pom.xml clean package
RUN mv /home/app/target/*.jar ./app.jar
EXPOSE 8080

ENTRYPOINT ["java","-Dmaven.test.skip", "-Dspring.profiles.active=${ENV:prod}","-XX:+UseSerialGC","-jar","./app.jar"]

