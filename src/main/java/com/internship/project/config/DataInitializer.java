package com.internship.project.config;

import com.internship.project.entity.RoleEntity;
import com.internship.project.enums.RoleName;
import com.internship.project.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Khởi tạo dữ liệu
@Configuration
public class DataInitializer {

    // Tạo vai trò mặc định
    @Bean
    public CommandLineRunner initRoles(RoleRepository roleRepository) {
        return args -> {
            if (roleRepository.findByName(RoleName.USER).isEmpty()) {
                RoleEntity userRole = new RoleEntity();
                userRole.setName(RoleName.USER);
                roleRepository.save(userRole);
            }
            if (roleRepository.findByName(RoleName.MANAGER).isEmpty()) {
                RoleEntity managerRole = new RoleEntity();
                managerRole.setName(RoleName.MANAGER);
                roleRepository.save(managerRole);
            }
        };
    }
}
