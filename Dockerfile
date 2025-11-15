# 1. Базовий image з Java 17
FROM eclipse-temurin:17-jdk-alpine

# 2. Директорія всередині контейнера
WORKDIR /app

# 3. Артефакт, який будемо копіювати (jar після maven build)
ARG JAR_FILE=target/speech_recognition-0.0.1-SNAPSHOT.jar

# 4. Копіюємо jar у контейнер
COPY ${JAR_FILE} app.jar

# 5. Вказуємо порт для інформації (не обов'язково, але ок)
EXPOSE 8080

# 6. Команда запуску
ENTRYPOINT ["java","-jar","/app.jar"]
