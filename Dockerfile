FROM openjdk:11-jre-slim
MAINTAINER soroosh.sarabadani@gmail.com

EXPOSE 8080

WORKDIR /opt/program
COPY target/jangul-service.jar /opt/program/

CMD java -XX:MaxRAMPercentage=80-XX:MinRAMPercentage=80 -XX:InitialRAMPercentage=80 -jar jangul-service.jar
