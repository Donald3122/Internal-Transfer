# -------------------
# Этап сборки
# -------------------
FROM maven:3.9.4-eclipse-temurin-17 AS build

WORKDIR /app

# Копируем pom.xml и скачиваем зависимости (для оффлайн сборки можно использовать локальный репозиторий Maven)
COPY pom.xml .

RUN mvn dependency:go-offline -B

# Копируем весь исходный код
COPY src ./src

# Сборка JAR, пропускаем тесты для скорости
RUN mvn clean package -DskipTests

# -------------------
# Этап запуска
# -------------------
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Копируем собранный JAR из предыдущего этапа
COPY --from=build /app/target/Internal-Transfers-1.0.0.jar app.jar

# Устанавливаем профиль Docker
ENV SPRING_PROFILES_ACTIVE=docker

# Запуск приложения
ENTRYPOINT ["java","-jar","app.jar"]