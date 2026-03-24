Tuần 8 – Unit Test & Refactor

Viết unit test cho TaskService, mock các dependency bằng Mockito, refactor code trùng lặp.

1. Unit test – TaskService:

 Test 1 – `createTask_shouldSetDefaultStatusTodo`: tạo Task mới, kiểm tra status mặc định là TODO. Dùng `when(projectRepository.findById(...))` và verify `taskRepository.save()` được gọi đúng.

 Test 2 – `assignTask_shouldFailWhenUserNotInProject`: giao Task cho User không thuộc Project, kiểm tra CustomException được ném ra.

 Test 3 – `updateStatus_shouldBlockIfTaskAlreadyDone`: cập nhật trạng thái Task đã DONE, kiểm tra CustomException được ném ra.

 File: `src/test/java/com/internship/project/service/TaskServiceTest.java`

2. Công cụ:

 JUnit 5 (`@Test`, `@BeforeEach`, `@ExtendWith(MockitoExtension.class)`)
 Mockito (`@Mock`, `@InjectMocks`, `when()`, `verify()`)
 `assertThrows()` để kiểm tra các trường hợp lỗi.

3. Refactor:

 Tách logic nghiệp vụ khỏi controller, đưa vào service layer.
 Đổi tên biến và hàm theo pattern rõ nghĩa: prefix `find`, `create`, `update`, `assign`.
 Loại bỏ code trùng lặp trong các service bằng cách tách hàm `findById` riêng.
