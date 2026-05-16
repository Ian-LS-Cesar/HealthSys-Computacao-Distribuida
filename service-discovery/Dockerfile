FROM eclipse-temurin:21-jdk AS builder
WORKDIR /app

COPY .mvn ./.mvn
COPY mvnw ./
COPY pom.xml ./

RUN chmod +x mvnw && ./mvnw dependency:go-offline -B

COPY src ./src

RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:21 AS runner

WORKDIR /app
COPY --from=builder ./app/target/service-discovery-0.0.1-SNAPSHOT.jar ./app.jar

EXPOSE 8761

ENTRYPOINT ["java", "-jar", "app.jar"]