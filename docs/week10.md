Tuần 10 – Tổng kết

Chuẩn bị tài liệu kiến trúc, sơ đồ JWT, script demo và tổng kết dự án.

1. Kiến trúc hệ thống:

```
Client (Postman / Swagger)
    │
    ▼
JwtAuthenticationFilter  ←  kiểm tra Bearer token mỗi request
    │
    ▼
Controller Layer         ←  nhận request, gọi service, trả ApiResponse
    │
    ▼
Service Layer            ←  logic nghiệp vụ, validate rule, ném CustomException
    │
    ▼
Repository Layer         ←  JpaRepository, query DB
    │
    ▼
SQL Server (InternshipDB)
```

2. Flow JWT:

```
[Register]
POST /api/auth/register
  → hash password (BCrypt)
  → lưu user + role vào DB
  → trả về UserEntity

[Login]
POST /api/auth/login
  → xác thực username/password
  → tạo JWT token (HS256, 120 phút)
  → trả về token

[Request có bảo mật]
GET/POST /api/...
  Header: Authorization: Bearer <token>
  → JwtAuthenticationFilter đọc token
  → validate chữ ký + thời hạn
  → load UserDetails từ DB
  → set vào SecurityContext
  → request đến controller
```

3. Script demo (checklist):

 Đăng ký tài khoản MANAGER và USER qua `POST /api/auth/register`.
 Đăng nhập lấy token qua `POST /api/auth/login`.
 Tạo Project mới bằng token MANAGER qua `POST /api/projects`.
 Thêm USER vào Project qua `POST /api/projects/{id}/members`.
 Tạo Task trong Project bằng token MANAGER qua `POST /api/tasks`.
 Giao Task cho USER qua `PUT /api/tasks/{id}/assign`.
 USER cập nhật trạng thái Task của mình qua `PUT /api/tasks/{id}/status`.
 Kiểm tra USER không tạo được Project (lỗi 403).
 Kiểm tra USER không xem được Task của người khác (lỗi 403).

4. Tổng kết:

 Các kỹ thuật áp dụng: Spring Boot MVC, JPA/Hibernate, Spring Security, JWT, BCrypt, Bean Validation, Mockito.
 Điểm quan trọng học được: phân layer rõ ràng (Controller–Service–Repository), xử lý lỗi tập trung, phân quyền theo role, viết unit test độc lập với database.
 Hạn chế: chưa có refresh token, chưa có pagination cho danh sách, chưa có integration test.
