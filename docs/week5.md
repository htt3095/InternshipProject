Tuần 5 – Task Business Rules

Phân tích và triển khai các rule nghiệp vụ cho Task: flow trạng thái, quy tắc giao việc, phân quyền endpoint.

1. Rule nghiệp vụ:

 Flow trạng thái Task: TODO → IN_PROGRESS → DONE. Khi Task đã ở trạng thái DONE thì không được cập nhật tiếp.

 Giao việc (assign): chỉ giao Task cho User đang là thành viên active của Project đó. Nếu User không thuộc Project thì trả về lỗi 400.

 Tạo Task: phải cung cấp projectId hợp lệ. Nếu Project không tồn tại thì trả về lỗi 404.

 Xem Task theo User: USER chỉ xem được Task của chính mình. MANAGER xem được Task của bất kỳ User nào.

2. Danh sách endpoint:

 `POST /api/tasks` – tạo Task (chỉ MANAGER).
 `PUT /api/tasks/{taskId}/assign` – giao Task cho User (chỉ MANAGER).
 `PUT /api/tasks/{taskId}/status` – cập nhật trạng thái Task.
 `GET /api/tasks/by-project/{projectId}` – lấy Task theo Project.
 `GET /api/tasks/by-user/{userId}` – lấy Task theo User.
 `GET /api/tasks/by-status?status=TODO` – lấy Task theo trạng thái.

3. File tham chiếu:

 Rule DONE block: `TaskService.updateStatus()` – `src/.../service/TaskService.java`
 Rule assign: `TaskService.assignTask()` – kiểm tra qua `ProjectMemberRepository`
 Phân quyền endpoint: `@PreAuthorize("hasRole('MANAGER')")` trong `TaskController.java`
