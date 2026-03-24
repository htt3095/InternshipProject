package com.internship.project.service;

import com.internship.project.dto.TaskCreateRequest;
import com.internship.project.entity.ProjectEntity;
import com.internship.project.entity.TaskEntity;
import com.internship.project.entity.UserEntity;
import com.internship.project.enums.TaskStatus;
import com.internship.project.exception.CustomException;
import com.internship.project.repository.ProjectMemberRepository;
import com.internship.project.repository.ProjectRepository;
import com.internship.project.repository.TaskRepository;
import com.internship.project.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;
    @Mock
    private ProjectRepository projectRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ProjectMemberRepository projectMemberRepository;

    @InjectMocks
    private TaskService taskService;

    private ProjectEntity project;

    @BeforeEach
    void setup() {
        project = new ProjectEntity("P1", "Demo");
    }

    @Test
    void createTask_shouldSetDefaultStatusTodo() {
        TaskCreateRequest request = new TaskCreateRequest("T1", "Desc", 1L, LocalDate.now().plusDays(1));
        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));
        when(taskRepository.save(any(TaskEntity.class))).thenAnswer(inv -> inv.getArgument(0));

        TaskEntity created = taskService.create(request);

        assertEquals(TaskStatus.TODO, created.getStatus());
        verify(taskRepository).save(any(TaskEntity.class));
    }

    @Test
    void assignTask_shouldFailWhenUserNotInProject() {
        TaskEntity task = new TaskEntity();
        task.setProject(project);
        UserEntity user = new UserEntity("u1", "u1@mail.com", "123456");

        when(taskRepository.findById(10L)).thenReturn(Optional.of(task));
        when(userRepository.findById(2L)).thenReturn(Optional.of(user));
        when(projectMemberRepository.existsByProjectIdAndUserIdAndActiveTrue(null, 2L)).thenReturn(false);

        assertThrows(CustomException.class, () -> taskService.assignTask(10L, 2L));
    }

    @Test
    void updateStatus_shouldBlockIfTaskAlreadyDone() {
        TaskEntity task = new TaskEntity();
        task.setStatus(TaskStatus.DONE);
        when(taskRepository.findById(5L)).thenReturn(Optional.of(task));

        assertThrows(CustomException.class, () -> taskService.updateStatus(5L, "IN_PROGRESS"));
    }
}
