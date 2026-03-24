Tuần 6 – Validate & Exception Handling

Thêm validate dữ liệu đầu vào, xây dựng xử lý lỗi tập trung và chuẩn hóa cấu trúc phản hồi API.

1. Validate dữ liệu:

 TaskCreateRequest: `@NotBlank @Size(max=200)` cho title, `@Size(max=1000)` cho description, `@NotNull` cho projectId, `@NotNull @Future` cho deadline (deadline phải sau ngày hiện tại). File: `src/.../dto/TaskCreateRequest.java`

 RegisterRequest: `@NotBlank @Size(min=3, max=100)` cho username, `@NotBlank @Email` cho email, `@NotBlank @Size(min=6, max=120)` cho password. File: `src/.../dto/RegisterRequest.java`

 LoginRequest: `@NotBlank` cho cả username và password. File: `src/.../dto/LoginRequest.java`

2. Exception handling:

 CustomException: RuntimeException có thêm `HttpStatus`, dùng để ném lỗi có HTTP code cụ thể (400, 404, 409...). File: `src/.../exception/CustomException.java`

 GlobalExceptionHandler: dùng `@RestControllerAdvice` bắt ba loại lỗi:
  - CustomException → trả về đúng HTTP code kèm message
  - MethodArgumentNotValidException → 400 kèm map field-error
  - Exception (catch-all) → 500 kèm message hệ thống

 File: `src/.../exception/GlobalExceptionHandler.java`

3. Chuẩn hóa phản hồi:

 ApiResponse<T>: gồm ba field `code`, `message`, `data`. Toàn bộ controller đều trả về kiểu này. File: `src/.../dto/ApiResponse.java`
