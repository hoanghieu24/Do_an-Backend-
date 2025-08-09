# Bước 1: Dùng image Java
FROM openjdk:17-jdk-slim

# Bước 2: Tạo thư mục để chứa app trong container
WORKDIR /app

# Bước 3: Copy file .jar vào container
COPY target/*.jar app.jar

# Bước 4: Chạy app khi container khởi động
ENTRYPOINT ["java", "-jar", "app.jar"]
