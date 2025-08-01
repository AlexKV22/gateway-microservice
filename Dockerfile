FROM eclipse-temurin:18-jre
WORKDIR /app
COPY target/gateway-microservice-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]