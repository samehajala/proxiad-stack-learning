# Use an official OpenJDK runtime as a parent image
FROM openjdk:21-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the application JAR file to the container
COPY target/*.jar app.jar

# Expose the application port (adjust based on your Spring Boot configuration)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
