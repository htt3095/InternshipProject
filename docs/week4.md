Tuần 4 – JPA Mapping & Task/Project API

Hoàn thiện mapping JPA cho Project và Task, xây dựng API quản lý Project và Task.

1. Kết quả:

 ProjectEntity: mapping bảng `projects`, có constructor validate tên không trống. File: `src/.../entity/ProjectEntity.java`

 TaskEntity: mapping bảng `tasks`, quan hệ ManyToOne tới Project (lazy) và User assignee (lazy). File: `src/.../entity/TaskEntity.java`

 Lazy/Eager: Project và assignee dùng FetchType.LAZY để tránh query thừa. Roles của User dùng FetchType.EAGER vì cần load khi xác thực.

 ProjectMemberEntity: mapping bảng `project_members`, có flag active để quản lý trạng thái thành viên. File: `src/.../entity/ProjectMemberEntity.java`

 TaskRepository: có các query `findByProjectId`, `findByAssigneeId`, `findByStatus`. File: `src/.../repository/TaskRepository.java`

 ProjectService: tạo project, lấy danh sách, tìm theo ID, thêm thành viên. File: `src/.../service/ProjectService.java`

 TaskService: tạo task, giao việc, cập nhật trạng thái, lấy theo project/user/status. File: `src/.../service/TaskService.java`

 TaskController: API tạo task, giao việc, cập nhật trạng thái, lấy theo project/user/status. File: `src/.../controller/TaskController.java`

 ProjectController: API tạo project, lấy danh sách, thêm thành viên. File: `src/.../controller/ProjectController.java`
