FROM openjdk:8-jre-alpine
COPY ["target/spring-application-0.0.1-SNAPSHOT.jar", "app.jar"]
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]