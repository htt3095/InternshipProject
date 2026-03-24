Tuần 7 – Security & JWT

Thêm phân quyền theo Role, triển khai đăng ký/đăng nhập với JWT và bảo vệ các endpoint.

1. Thiết kế Role:

 Bảng `roles` lưu hai vai trò: USER và MANAGER.
 Bảng `user_roles` là bảng trung gian nối `users` và `roles` (many-to-many).
 RoleEntity: file `src/.../entity/RoleEntity.java`
 Enum RoleName: USER, MANAGER – file `src/.../enums/RoleName.java`

2. Đăng ký & đăng nhập:

 `POST /api/auth/register` – nhận RegisterRequest, hash password bằng BCrypt, lưu user và role.
 `POST /api/auth/login` – xác thực username/password, trả về JWT token nếu đúng.
 AuthController: `src/.../controller/AuthController.java`
 AuthService: `src/.../service/AuthService.java`

3. JWT:

 JwtUtil: tạo token (generate), lấy username từ token (extract), kiểm tra hợp lệ và hết hạn (validate). File: `src/.../security/JwtUtil.java`
 JwtAuthenticationFilter: đọc header `Authorization: Bearer <token>`, validate và set vào SecurityContext. File: `src/.../security/JwtAuthenticationFilter.java`
 Token hết hạn sau 120 phút (cấu hình trong application.yml).

4. SecurityConfig:

 Endpoint public: `/api/auth/**`, `/swagger-ui/**`, `/v3/api-docs/**`.
 Các endpoint còn lại yêu cầu token hợp lệ.
 `@PreAuthorize("hasRole('MANAGER')")` bảo vệ các API tạo project, tạo task, giao việc.
 USER chỉ xem được Task của chính mình (kiểm tra trong TaskController).
 File: `src/.../config/SecurityConfig.java`
