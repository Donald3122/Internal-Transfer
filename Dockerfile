FROM maven:3.9.4-eclipse-temurin-17 AS build
# Рабочая директория внутри контейнера
WORKDIR /app

# Копируем pom.xml и скачиваем зависимости
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Копируем весь исходный код
COPY src ./src

# Сборка JAR (пропускаем тесты для скорости)
RUN mvn clean package -DskipTests

# -------------------
# Этап 2: запуск приложения
# -------------------
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Копируем JAR из предыдущего этапа
COPY --from=build /app/target/Internal-Transfers-1.0.0.jar app.jar

# Устанавливаем профиль для Docker (можно в application-docker.properties настроить)
ENV SPRING_PROFILES_ACTIVE=docker

# Запуск приложения
ENTRYPOINT ["java","-jar","app.jar"]