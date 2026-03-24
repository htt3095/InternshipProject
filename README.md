Đây là project backend Spring Boot quản lý User, Project và Task. Database dùng SQL Server.

1. Các chức năng chính:

 Quản lý tài khoản: Đăng ký, đăng nhập, bảo mật bằng JWT token.

 Phân quyền (Role-based): Dùng Spring Security để chia quyền, ví dụ chỉ MANAGER mới tạo được project.

 Quản lý dự án (Project): Tạo project, thêm thành viên vào project.

 Quản lý công việc (Task): Tạo task, giao việc cho user, cập nhật trạng thái (TODO, IN_PROGRESS, DONE).

 Validate dữ liệu: Bắt lỗi tập trung qua Global Exception Handling, trả về message rõ ràng.

2. Stack công nghệ:

 Ngôn ngữ: Java 17

 Framework: Spring Boot 4.0.2

 Database: SQL Server (SSMS 20)

 Authentication: Spring Security & JWT

 Document API: Swagger UI

3. Hướng dẫn chạy dự án ở máy Local:

B1 Clone source code về máy.

B2 Mở SSMS, kết nối tài khoản `sa/1234` và tạo database mới tên `InternshipDB`.

B3 Chạy file `sql/01_schema.sql` để tạo bảng, sau đó chạy `sql/02_seed_data.sql` để thêm data mẫu.

B4 Mở file `src/main/resources/application-dev.yml`, kiểm tra lại thông tin kết nối DB cho đúng với máy.

B5 Build và chạy bằng lệnh `mvn clean spring-boot:run`.

B6 Sau khi server chạy ở port 8080, mở trình duyệt vào `http://localhost:8080/swagger-ui/index.html` để xem danh sách API và test thử.

