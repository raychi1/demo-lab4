# Етап збірки
FROM maven:3.8.4-openjdk-17 AS builder

# Копіюємо файли проекту в контейнер
COPY ./ /app
WORKDIR /app

# Збираємо проект Maven
RUN mvn clean package -DskipTests

# Етап запуску
FROM openjdk:17-jdk-alpine

# Копіюємо JAR-файл з етапу збірки у контейнер
COPY --from=builder /app/target/*.jar /app/app.jar

# Запускаємо нашу Java-програму при старті контейнера
CMD ["java", "-jar", "/app/app.jar"]
