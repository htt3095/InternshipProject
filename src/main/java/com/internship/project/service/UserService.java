package com.internship.project.service;

import com.internship.project.dto.CreateUserRequest;
import com.internship.project.dto.UpdateUserRequest;
import com.internship.project.entity.RoleEntity;
import com.internship.project.entity.UserEntity;
import com.internship.project.enums.RoleName;
import com.internship.project.exception.CustomException;
import com.internship.project.repository.RoleRepository;
import com.internship.project.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

// Service người dùng
@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Tạo người dùng
    public UserEntity create(CreateUserRequest request) {
        if (userRepository.existsByUsername(request.username())) {
            throw new CustomException(HttpStatus.CONFLICT, "Tên đăng nhập đã tồn tại");
        }
        if (userRepository.existsByEmail(request.email())) {
            throw new CustomException(HttpStatus.CONFLICT, "Email đã tồn tại");
        }

        RoleEntity userRole = roleRepository.findByName(RoleName.USER)
            .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Vai trò USER không tồn tại"));
        UserEntity user = new UserEntity(request.username(), request.email(), passwordEncoder.encode(request.password()));
        user.getRoles().add(userRole);
        return userRepository.save(user);
    }

    // Lấy tất cả
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    // Tìm theo ID
    public UserEntity findById(Long id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Người dùng không tồn tại"));
    }

    // Cập nhật người dùng
    public UserEntity update(Long id, UpdateUserRequest request) {
        UserEntity user = findById(id);
        if (!user.getUsername().equals(request.username()) && userRepository.existsByUsername(request.username())) {
            throw new CustomException(HttpStatus.CONFLICT, "Tên đăng nhập đã tồn tại");
        }
        if (!user.getEmail().equals(request.email()) && userRepository.existsByEmail(request.email())) {
            throw new CustomException(HttpStatus.CONFLICT, "Email đã tồn tại");
        }
        user.setUsername(request.username());
        user.setEmail(request.email());
        return userRepository.save(user);
    }

    // Xóa người dùng
    public void delete(Long id) {
        findById(id);
        userRepository.deleteById(id);
    }
}
