FROM openjdk:11.0.7-jdk-slim
ARG JAR_FILE=target/*.jar
ENV DB_USERNAME=postgres
ENV DB_PASSWORD=postgrespw
ENV DB_NAME=newsportal
ENV DB_HOST=localhost
ENV DB_PORT=5432
ENV APP_PORT=8080
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Dspring.datasource.password=${DB_PASSWORD}", "-Dspring.datasource.username=${DB_USERNAME}", "-Dspring.datasource.url=jdbc:postgresql://${DB_HOST}:${DB_PORT}/${DB_NAME}", "-Dserver.port=${APP_PORT}", "-jar", "app.jar"]