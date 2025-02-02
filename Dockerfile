# Use a base image with Java
FROM openjdk:17-jdk-slim

# Install curl
RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*

# Add the Spring Boot JAR file to the image
COPY target/products-service-0.0.1-SNAPSHOT.jar /app/products-service-0.0.1-SNAPSHOT.jar

# Expose the port your application will run on
EXPOSE 8085

# Set the command to run the Spring Boot application
CMD ["java", "-jar", "/app/products-service-0.0.1-SNAPSHOT.jar"]
