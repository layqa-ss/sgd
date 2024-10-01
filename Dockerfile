# Use a base image with Java 17
FROM openjdk:17-jdk-slim

# Copy the WAR package into the image
ARG WAR_FILE=sgd-webapp/target/sgd-webapp-0.0.1.war
COPY ${WAR_FILE} sgd-webapp-0.0.1.war

# Expose the application port
EXPOSE 8090

# Run the App
ENTRYPOINT ["java", "-jar", "sgd-webapp-0.0.1.war"]
