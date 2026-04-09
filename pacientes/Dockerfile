FROM gradle:9.4.1-jdk17 AS builder

WORKDIR /app

COPY build.gradle .
COPY settings.gradle .
COPY src ./src

RUN gradle clean build -x test --no-daemon

FROM eclipse-temurin:17-jdk AS runner

WORKDIR /app

COPY --from=builder /app/build/libs/pacientes-0.0.1-SNAPSHOT.jar ./app.jar

EXPOSE 4000

ENTRYPOINT ["java", "-jar", "app.jar"]
