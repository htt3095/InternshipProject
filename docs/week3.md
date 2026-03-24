Tuần 3 – Spring Boot & User API

Khởi tạo project Spring Boot, cấu hình kết nối database, xây dựng API cho User.

1. Cấu trúc package:

```
src/main/java/com/internship/project/
    config/
    controller/
    dto/
    entity/
    enums/
    exception/
    repository/
    security/
    service/
```

2. Kết quả:

 Khởi tạo project: Spring Boot 4.0.2, Java 17, kết nối SQL Server qua JPA. File: `pom.xml`

 Config database: profile dev dùng `sa/1234` local, profile prod dùng biến môi trường. File: `src/main/resources/`

 Kiểm tra startup: server khởi động trên port 8080 không lỗi.

 UserEntity: mapping vào bảng `users`. File: `src/.../entity/UserEntity.java`

 UserRepository: extends JpaRepository, có `findByUsername`. File: `src/.../repository/UserRepository.java`

 UserService: logic tìm user theo ID và username. File: `src/.../service/UserService.java`

 UserController: API lấy danh sách user (`GET /api/users`). File: `src/.../controller/UserController.java`

 Xử lý lỗi: GlobalExceptionHandler bắt lỗi validation (400), lỗi tùy chỉnh, và lỗi hệ thống (500). File: `src/.../exception/GlobalExceptionHandler.java`

 Refactor naming: suffix Entity, Request, Service, Controller thống nhất toàn dự án.
