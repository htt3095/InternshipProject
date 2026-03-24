Tuần 1 – Phân tích domain & OOP

Phân tích yêu cầu nghiệp vụ, xác định các entity và enum, sau đó triển khai các class Java tương ứng với logic cơ bản.

1. Kết quả:

 Ghi chú nghiệp vụ: Hệ thống gồm ba đối tượng chính là User, Project và Task. User có thể được giao nhiều Task, mỗi Task thuộc một Project.

 Enum TaskStatus: TODO, IN_PROGRESS, DONE – dùng để theo dõi trạng thái công việc. File: `src/.../enums/TaskStatus.java`

 Enum RoleName: USER, MANAGER – dùng để phân quyền trong hệ thống. File: `src/.../enums/RoleName.java`

 UserEntity: gồm các field username, email, password, roles. Có constructor validate dữ liệu đầu vào (username không trống, email hợp lệ, password tối thiểu 6 ký tự). File: `src/.../entity/UserEntity.java`

 TaskEntity: gồm các field title, description, status, deadline, project, assignee. Status mặc định là TODO. File: `src/.../entity/TaskEntity.java`

 ProjectEntity: gồm name và description. Constructor kiểm tra tên không được để trống. File: `src/.../entity/ProjectEntity.java`

 Tách trách nhiệm: Controller / Service / Repository / Entity được tách thành các layer riêng.

 Logic Task: tạo, giao việc cho User, cập nhật trạng thái. File: `src/.../service/TaskService.java`

 Xử lý lỗi: CustomException và GlobalExceptionHandler để bắt lỗi tập trung. File: `src/.../exception/`
