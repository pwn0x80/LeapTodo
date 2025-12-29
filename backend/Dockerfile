FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN ls
RUN mvn clean package -DskipTests
RUN ls target
FROM openjdk:17.0.1-jdk-slim
COPY --from=build app/target/TODOBACKEND-0.0.1-SNAPSHOT.jar demo.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","demo.jar"]
