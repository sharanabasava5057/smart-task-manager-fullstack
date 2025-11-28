package com.example.smarttaskmanager.controller;

import com.example.smarttaskmanager.dto.AuthRequest;
import com.example.smarttaskmanager.dto.AuthResponse;
import com.example.smarttaskmanager.dto.RegisterRequest;
import com.example.smarttaskmanager.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;  // 🔥 IMPORTANT: this fixes the Map error

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // REGISTER
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        authService.register(request);
        return ResponseEntity.ok("Registration successful");
    }

    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        AuthResponse response = authService.authenticate(request);
        return ResponseEntity.ok(response);
    }

    // FORGOT PASSWORD - backend expects JSON body { "email": "..." }
    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        authService.sendResetLink(email);
        return ResponseEntity.ok("Reset link sent");
    }

    // RESET PASSWORD - backend expects JSON body { "token": "...", "newPassword": "..." }
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody Map<String, String> body) {
        String token = body.get("token");
        String newPassword = body.get("newPassword");
        authService.resetPassword(token, newPassword);
        return ResponseEntity.ok("Password reset successful");
    }
}
