### Build stage ###
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests


### Runtime stage ###
FROM openjdk:17-slim AS runtime

ENV SERVER_PORT=8081

WORKDIR /app

COPY docker/prod/prepare_app.sh ./prepare_app.sh
RUN chmod +x ./prepare_app.sh

RUN addgroup --system spring && adduser --system --group spring
USER spring:spring

COPY --from=build /app/target/*.jar app.jar

EXPOSE ${SERVER_PORT}

LABEL org.opencontainers.image.title="booklub-api"
LABEL org.opencontainers.image.description="Backend API for Booklub"
LABEL org.opencontainers.image.environment="DB_URL, DB_USERNAME, DB_PASSWORD, KEYCLOAK_CLIENT_ID, KEYCLOAK_CLIENT_SECRET, KEYCLOAK_REALM, KEYCLOAK_AUTH_URL, S3_URL, S3_ACCESS_NAME, S3_ACCESS_SECRET"

ENTRYPOINT ["./prepare_app.sh"]