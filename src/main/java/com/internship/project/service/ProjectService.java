package com.internship.project.service;

import com.internship.project.dto.ProjectRequest;
import com.internship.project.dto.UpdateProjectRequest;
import com.internship.project.entity.ProjectEntity;
import com.internship.project.entity.ProjectMemberEntity;
import com.internship.project.entity.UserEntity;
import com.internship.project.exception.CustomException;
import com.internship.project.repository.ProjectMemberRepository;
import com.internship.project.repository.ProjectRepository;
import com.internship.project.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

// Service dự án
@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final ProjectMemberRepository projectMemberRepository;

    public ProjectService(ProjectRepository projectRepository,
                          UserRepository userRepository,
                          ProjectMemberRepository projectMemberRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.projectMemberRepository = projectMemberRepository;
    }

    // Tạo dự án
    public ProjectEntity create(ProjectRequest request) {
        return projectRepository.save(new ProjectEntity(request.name(), request.description()));
    }

    // Lấy tất cả
    public List<ProjectEntity> findAll() {
        return projectRepository.findAll();
    }

    // Tìm theo ID
    public ProjectEntity findById(Long id) {
        return projectRepository.findById(id)
            .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Dự án không tồn tại"));
    }

    // Cập nhật dự án
    public ProjectEntity update(Long id, UpdateProjectRequest request) {
        ProjectEntity project = findById(id);
        project.setName(request.name());
        project.setDescription(request.description());
        return projectRepository.save(project);
    }

    // Xóa dự án
    public void delete(Long id) {
        findById(id);
        projectRepository.deleteById(id);
    }

    // Thêm thành viên
    public void addMember(Long projectId, Long userId) {
        ProjectEntity project = findById(projectId);
        UserEntity user = userRepository.findById(userId)
            .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Người dùng không tồn tại"));

        if (projectMemberRepository.existsByProjectIdAndUserIdAndActiveTrue(projectId, userId)) {
            throw new CustomException(HttpStatus.CONFLICT, "Người dùng đã là thành viên dự án");
        }

        ProjectMemberEntity member = new ProjectMemberEntity();
        member.setProject(project);
        member.setUser(user);
        member.setActive(true);
        projectMemberRepository.save(member);
    }
}
