FROM openjdk:8-jre-alpine
VOLUME /tmp
ADD /target/Carros-0.0.1-SNAPSHOT app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
