# ---------- STAGE 1: Build ----------
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Копіюємо pom.xml і завантажуємо залежності
COPY pom.xml .
RUN mvn -B dependency:resolve dependency:resolve-plugins

# Копіюємо весь код
COPY src ./src

# Будуємо JAR
RUN mvn clean package -DskipTests

# ---------- STAGE 2: Runtime ----------
FROM eclipse-temurin:17-jre
WORKDIR /app

# Копіюємо побудований JAR з попереднього шару
COPY --from=build /app/target/*.jar app.jar

# Динамічний порт (Render передасть PORT)
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
