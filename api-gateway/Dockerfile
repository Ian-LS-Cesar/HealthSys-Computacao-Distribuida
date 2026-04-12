FROM eclipse-temurin:17-jdk AS builder
WORKDIR /app

COPY gradlew .
COPY gradle ./gradle
COPY build.gradle .
COPY settings.gradle .
COPY src ./src

RUN chmod +x gradlew \
    && ./gradlew clean bootJar -x test --no-daemon

FROM eclipse-temurin:17-jre AS runner
WORKDIR /app

COPY --from=builder /app/build/libs/api-gateway-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
