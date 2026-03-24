package com.internship.project.service;

import com.internship.project.dto.AuthResponse;
import com.internship.project.dto.LoginRequest;
import com.internship.project.dto.RegisterRequest;
import com.internship.project.entity.RoleEntity;
import com.internship.project.entity.UserEntity;
import com.internship.project.enums.RoleName;
import com.internship.project.exception.CustomException;
import com.internship.project.repository.RoleRepository;
import com.internship.project.repository.UserRepository;
import com.internship.project.security.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

// Service xác thực
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager,
                       JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    // Đăng ký tài khoản
    public UserEntity register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.username())) {
            throw new CustomException(HttpStatus.CONFLICT, "Tên đăng nhập đã tồn tại");
        }
        if (userRepository.existsByEmail(request.email())) {
            throw new CustomException(HttpStatus.CONFLICT, "Email đã tồn tại");
        }

        RoleName roleName = "MANAGER".equalsIgnoreCase(request.role()) ? RoleName.MANAGER : RoleName.USER;
        RoleEntity role = roleRepository.findByName(roleName)
            .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Vai trò không tồn tại: " + roleName));

        UserEntity user = new UserEntity(request.username(), request.email(), passwordEncoder.encode(request.password()));
        user.getRoles().add(role);
        return userRepository.save(user);
    }

    // Đăng nhập hệ thống
    public AuthResponse login(LoginRequest request) {
        Authentication auth = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        String token = jwtUtil.generateToken(userDetails);
        return new AuthResponse(token, "Bearer", userDetails.getUsername());
    }
}
