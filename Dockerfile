FROM maven:3.9.8 AS build
WORKDIR /app
COPY pom.xml .
COPY sgd-domain ./sgd-domain
COPY sgd-services ./sgd-services
COPY sgd-webapp ./sgd-webapp
RUN mvn clean install

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/sgd-webapp/target/sgd-webapp-0.0.1.war .
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "sgd-webapp-0.0.1.war"]
