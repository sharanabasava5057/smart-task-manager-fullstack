package com.example.smarttaskmanager.service;

import com.example.smarttaskmanager.entity.ActivityLog;
import com.example.smarttaskmanager.entity.Role;
import com.example.smarttaskmanager.entity.User;
import com.example.smarttaskmanager.repository.ActivityLogRepository;
import com.example.smarttaskmanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ActivityLogRepository logRepository;

    // Log action
    private void log(String action, String admin) {
        ActivityLog log = ActivityLog.builder()
                .action(action)
                .performedBy(admin)
                .timestamp(LocalDateTime.now())
                .build();
        logRepository.save(log);
    }

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Add new user
    public User createUser(String fullName, String email, String password, String role, String admin) {

        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("User already exists");
        }

        User user = User.builder()
                .fullName(fullName)
                .email(email)
                .password(passwordEncoder.encode(password))
                .enabled(true)
                .roles(Set.of(Role.valueOf(role)))
                .build();

        userRepository.save(user);
        log("Created user: " + email, admin);

        return user;
    }

    // Update user role
    public User updateRole(Long id, String role, String admin) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.getRoles().clear();
        user.getRoles().add(Role.valueOf(role));
        userRepository.save(user);

        log("Updated role for user ID " + id, admin);

        return user;
    }

    // Delete user
    public void deleteUser(Long id, String admin) {
        userRepository.deleteById(id);
        log("Deleted user ID " + id, admin);
    }

    // Get Logs
    public List<ActivityLog> getActivityLogs() {
        return logRepository.findAll();
    }
}
