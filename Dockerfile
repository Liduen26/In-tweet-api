# Étape 1 : Construction de l'application
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn package -DskipTests

# Étape 2 : Création de l'image finale
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/*.jar in-tweet-api.jar

COPY entrypoint.sh entrypoint.sh
RUN chmod +x entrypoint.sh

# Installer le client MariaDB
RUN apt-get update && apt-get install -y mariadb-client && rm -rf /var/lib/apt/lists/*

COPY init.sql init.sql
RUN chmod +x init.sql

# Expose le port utilisé par Spring Boot
EXPOSE 8080

ENV DB_PORT=3306
ENV DB_HOST=mariadb
ENV DB_USER=user
ENV DB_PSW=psw
ENV DB_NAME=db
ENV INIT_DB=false


# Démarre l'application
ENTRYPOINT ["/app/entrypoint.sh"]