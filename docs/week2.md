Tuần 2 – Database & SQL

Chuyển đổi entity thành bảng cơ sở dữ liệu, vẽ ERD, viết SQL schema và insert dữ liệu test.

1. ERD (quan hệ giữa các bảng):

```
users ──< user_roles >── roles
users ──< project_members >── projects
projects ──< tasks
users ──< tasks (assignee)
```

 users – lưu thông tin tài khoản (id, username, email, password).
 roles – lưu vai trò (USER, MANAGER).
 user_roles – bảng trung gian nối users và roles (many-to-many).
 projects – lưu thông tin dự án (id, name, description).
 project_members – lưu thành viên của từng project (project_id, user_id, active).
 tasks – lưu công việc (id, title, description, status, deadline, project_id, assignee_id).

2. Kết quả:

 SQL tạo bảng: PK, FK, UNIQUE, INDEX đầy đủ. File: `sql/01_schema.sql`

 Constraint trạng thái Task: `CK_tasks_status CHECK (status IN ('TODO', 'IN_PROGRESS', 'DONE'))`

 Constraint deadline: `CK_deadline CHECK (deadline >= CAST(GETDATE() AS DATE))`

 Index: IX_tasks_project_id, IX_tasks_assignee_id, IX_tasks_status

 Dữ liệu test: 53 records bao gồm users, projects, project_members và tasks. File: `sql/02_seed_data.sql`

 Các query kiểm tra: task theo user, task theo project, task theo status. File: `sql/03_queries.sql`
