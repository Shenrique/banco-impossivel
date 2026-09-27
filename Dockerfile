# Etapa 1: build
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn -q dependency:go-offline
COPY src ./src
RUN mvn -q package -DskipTests

# Etapa 2: runtime enxuto, sem rodar como root
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
RUN addgroup -S banco && adduser -S banco -G banco
COPY --from=build /app/target/banco-impossivel-*.jar app.jar
USER banco
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
