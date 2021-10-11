FROM maven:3.8.3-openjdk-11-slim AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn -e -B dependency:resolve
COPY src ./src
COPY google_checks.xml .
RUN mvn -e -B package

FROM openjdk:11-jre-slim
COPY --from=builder /app/target/application.jar /
CMD ["java", "-jar", "/application.jar"]

EXPOSE 8080