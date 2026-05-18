# Mude de 17 para 21
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app
COPY .mvn ./.mvn
COPY mvnw ./
COPY pom.xml ./
RUN chmod +x mvnw && ./mvnw dependency:go-offline -B
COPY src ./src
RUN ./mvnw clean package -DskipTests

# Mude de 17 para 21
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8084
ENTRYPOINT ["java", "-jar", "app.jar"]