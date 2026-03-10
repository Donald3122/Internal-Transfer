Как запустить проект через Docker

Этот раздел предназначен для абсолютных новичков, чтобы они смогли поднять сервис без знаний Java и Spring Boot.

1️⃣ Установка необходимых инструментов

Для запуска проекта нужны:

Docker – платформа для контейнеризации приложений
Скачать: https://www.docker.com/get-started

Docker Compose – инструмент для запуска нескольких контейнеров одновременно
Обычно идёт в комплекте с Docker Desktop.

2️⃣ Проверка установки

Открой терминал (PowerShell / CMD / Linux Terminal) и выполни команды:

docker --version
docker-compose --version

Ты должен увидеть версии Docker и Docker Compose.

Если всё ок, продолжаем.

3️⃣ Структура проекта

В корне проекта должны быть эти файлы:

Internal-Transfers/
│
├── Dockerfile                 # Файл сборки приложения
├── docker-compose.yml         # Запуск контейнеров PostgreSQL + приложение
├── pom.xml                    # Maven проект
├── README.md                  # Документация (наш файл)
├── src/                       # Исходники Java
│   └── main/
│       ├── java/
│       └── resources/
│           ├── application.properties
│           └── application-docker.properties

Важно: Docker будет собирать проект прямо из этих файлов.

4️⃣ Настройка Docker Compose

Открой docker-compose.yml. Пример:

services:
  postgres:
    image: postgres:16
    container_name: internal-transfers-db
    environment:
      POSTGRES_USER: myuser
      POSTGRES_PASSWORD: mypassword
      POSTGRES_DB: internal_transfers
    ports:
      - "15432:5432"
    volumes:
      - postgres_data:/var/lib/postgresql/data
    healthcheck:
      test: ["CMD-SHELL", "pg_isready -U myuser"]
      interval: 10s
      timeout: 5s
      retries: 5

  app:
    build: .
    container_name: internal-transfers-app
    depends_on:
      postgres:
        condition: service_healthy
    ports:
      - "8080:8080"
    environment:
      SPRING_DATASOURCE_URL: jdbc:postgresql://postgres:5432/internal_transfers
      SPRING_DATASOURCE_USERNAME: myuser
      SPRING_DATASOURCE_PASSWORD: mypassword
      SPRING_JPA_HIBERNATE_DDL_AUTO: update
      SPRING_PROFILES_ACTIVE: docker

volumes:
  postgres_data:

Объяснение:

postgres – контейнер с базой данных

app – контейнер с приложением

depends_on и healthcheck – ждём пока база будет готова

ports – пробрасываем порты на локальный компьютер

5️⃣ Настройка свойств Spring Boot для Docker

В файле application-docker.properties:

spring.datasource.url=jdbc:postgresql://postgres:5432/internal_transfers
spring.datasource.username=myuser
spring.datasource.password=mypassword

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

Обратите внимание: используем имя сервиса postgres, а не localhost.

6️⃣ Сборка и запуск проекта

Открой терминал в папке проекта и выполни команду:

docker-compose up --build

Что происходит:

Docker скачает образы PostgreSQL и OpenJDK/Maven (если есть интернет)

Соберёт приложение из исходников (JAR файл)

Поднимет контейнер PostgreSQL

Поднимет контейнер приложения и подключит к базе

⚠️ Первое поднятие может занять несколько минут, особенно если Docker скачивает образы.

7️⃣ Проверка работы

После запуска:

Приложение доступно на: http://localhost:8080

PostgreSQL доступен на: localhost:15432 (логин: myuser, пароль: mypassword)

Пример запроса через Postman или браузер:

GET http://localhost:8080/api/account/KG18459521/statement?from=2026-03-01&to=2026-03-10
8️⃣ Остановка и очистка

Чтобы остановить приложение и базу:

docker-compose down

Если хочешь удалить данные базы:

docker-compose down -v


# Internal Transfers Service

REST-сервис для выполнения внутренних переводов денежных средств между счетами внутри банка.

## Основная функциональность

* Перевод средств между счетами
* Проверка бизнес-правил:

  * счета должны быть разными
  * на счёте достаточно средств
  * счёт активен
* Использование транзакций
* Сохранение статуса операции (SUCCESS / FAILED)

Технологии:

* Java 17
* Spring Boot
* Spring Data JPA
* PostgreSQL
* Docker
* Docker Compose

---

# Запуск проекта через Docker (с интернетом)

Для запуска требуется:

* Docker
* Docker Compose
* Интернет (для скачивания образов)

Запуск выполняется одной командой:

```
docker-compose up --build
```

После запуска:

* приложение доступно на порту **8080**
* PostgreSQL доступен на порту **15432**

```
http://localhost:8080
```

---

# Структура проекта

```
Internal-Transfers
│
├── src
│   └── main
│       ├── java
│       │
│       └── resources
│           ├── application.properties
│           └── application-docker.properties
│
├── Dockerfile
├── docker-compose.yml
├── pom.xml
└── README.md
```

---

# Dockerfile

Файл отвечает за сборку Spring Boot приложения внутри Docker.

```
FROM maven:3.9.4-eclipse-temurin-17 AS build

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline

COPY src ./src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY --from=build /app/target/Internal-Transfers-1.0.0.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]
```

Этапы:

1. Скачиваются зависимости Maven
2. Собирается JAR файл
3. Создаётся лёгкий runtime контейнер
4. Запускается приложение

---

# docker-compose.yml

Файл запускает **два контейнера**:

* PostgreSQL
* Spring Boot приложение

```
services:
  postgres:
    image: postgres:16
    container_name: internal-transfers-db
    environment:
      POSTGRES_USER: myuser
      POSTGRES_PASSWORD: mypassword
      POSTGRES_DB: internal_transfers
    ports:
      - "15432:5432"
    volumes:
      - postgres_data:/var/lib/postgresql/data
    healthcheck:
      test: ["CMD-SHELL", "pg_isready -U myuser"]
      interval: 10s
      timeout: 5s
      retries: 5

  app:
    build: .
    container_name: internal-transfers-app
    depends_on:
      postgres:
        condition: service_healthy
    ports:
      - "8080:8080"
    environment:
      SPRING_DATASOURCE_URL: jdbc:postgresql://postgres:5432/internal_transfers
      SPRING_DATASOURCE_USERNAME: myuser
      SPRING_DATASOURCE_PASSWORD: mypassword
      SPRING_JPA_HIBERNATE_DDL_AUTO: update
      SPRING_PROFILES_ACTIVE: docker

volumes:
  postgres_data:
```

---

# application.properties

Используется для **локального запуска приложения**.

```
spring.datasource.url=jdbc:postgresql://localhost:15432/internal_transfers
spring.datasource.username=myuser
spring.datasource.password=mypassword

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

# application-docker.properties

Используется при запуске через Docker.

```
spring.datasource.url=jdbc:postgresql://postgres:5432/internal_transfers
spring.datasource.username=myuser
spring.datasource.password=mypassword

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

В Docker используется имя сервиса **postgres**, а не localhost.

---

# Запуск проекта без интернета

Если интернет недоступен, можно использовать **локальную PostgreSQL**, установленную на компьютере.

## Шаг 1

Собрать JAR файл локально:

```
mvn clean package
```

После сборки появится файл:

```
target/Internal-Transfers-1.0.0.jar
```

---

## Шаг 2

Использовать упрощённый Dockerfile:

```
FROM openjdk:17

WORKDIR /app

COPY target/Internal-Transfers-1.0.0.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]
```

---

## Шаг 3

Использовать локальную PostgreSQL.

```
spring.datasource.url=jdbc:postgresql://host.docker.internal:15432/internal_transfers
spring.datasource.username=myuser
spring.datasource.password=mypassword
```

`host.docker.internal` позволяет контейнеру подключаться к базе данных, запущенной на компьютере.

---

# Проверка работы

После запуска сервиса можно выполнять REST-запрос:

```
POST /api/transfers
```

Пример JSON:

```
{
  "fromAccount": "1",
  "toAccount": "2",
  "amount": 100
}
```

---

# Остановка контейнеров

```
docker-compose down
```

---

# Автор

Darman Tynybekov
