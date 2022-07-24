FROM openjdk:13-jdk-alpine
COPY target/northwind-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]