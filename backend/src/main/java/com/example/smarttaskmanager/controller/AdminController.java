package com.example.smarttaskmanager.controller;

import com.example.smarttaskmanager.entity.ActivityLog;
import com.example.smarttaskmanager.entity.User;
import com.example.smarttaskmanager.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@Tag(name = "Admin", description = "Admin panel: Manage users, roles, and view logs")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final AdminService adminService;

    // ------------------------------
    // 1. Get all users
    // ------------------------------
    @Operation(summary = "Get all users")
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(adminService.getAllUsers());
    }

    // ------------------------------
    // 2. Add user
    // ------------------------------
    @Operation(summary = "Add a new user")
    @PostMapping("/add")
    public ResponseEntity<User> addUser(
            @RequestParam String fullName,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String role,
            Authentication auth
    ) {
        return ResponseEntity.ok(
                adminService.createUser(fullName, email, password, role, auth.getName())
        );
    }

    // ------------------------------
    // 3. Update user role
    // ------------------------------
    @Operation(summary = "Update a user's role")
    @PutMapping("/update-role/{id}")
    public ResponseEntity<User> updateRole(
            @PathVariable Long id,
            @RequestParam String role,
            Authentication auth
    ) {
        return ResponseEntity.ok(
                adminService.updateRole(id, role, auth.getName())
        );
    }

    // ------------------------------
    // 4. Delete user
    // ------------------------------
    @Operation(summary = "Delete a user")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(
            @PathVariable Long id,
            Authentication auth
    ) {
        adminService.deleteUser(id, auth.getName());
        return ResponseEntity.ok("User deleted");
    }

    // ------------------------------
    // 5. View Activity Logs
    // ------------------------------
    @Operation(summary = "View all activity logs")
    @GetMapping("/logs")
    public ResponseEntity<List<ActivityLog>> getLogs() {
        return ResponseEntity.ok(adminService.getActivityLogs());
    }
}
