Tuần 9 – Swagger & Triển khai

Tách profile cấu hình, tích hợp Swagger UI để document API, build JAR và kiểm tra chạy local.

1. Profile cấu hình:

 `application.yml` – cấu hình chung: port 8080, profile active mặc định là `dev`, JWT secret và expiration.
 `application-dev.yml` – cấu hình local: kết nối DB `sa/1234`, show-sql bật, ddl-auto = validate.
 `application-prod.yml` – cấu hình production: đọc DB URL, username, password từ biến môi trường (`DB_URL`, `DB_USERNAME`, `DB_PASSWORD`, `JWT_SECRET`).

2. Swagger UI:

 Dependency: `springdoc-openapi-starter-webmvc-ui` phiên bản 2.8.13.
 Truy cập: `http://localhost:8080/swagger-ui/index.html`
 Toàn bộ API (auth, users, projects, tasks) được document tự động.
 Endpoint Swagger được đặt là public trong SecurityConfig (không cần token để xem).

3. Build & chạy:

```bash
# Chạy local với profile dev
mvn clean spring-boot:run

# Build JAR
mvn clean package -DskipTests

# Chạy JAR
java -jar target/project-1.0.0.jar
```

 Sau khi chạy, mở trình duyệt vào `http://localhost:8080/swagger-ui/index.html` để test API.
