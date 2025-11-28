package com.example.smarttaskmanager.controller;

import com.example.smarttaskmanager.entity.User;
import com.example.smarttaskmanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @PostMapping("/notifications")
    public ResponseEntity<String> updateNotifications(@RequestParam String email,
                                                      @RequestParam boolean enabled) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setNotifyEnabled(enabled);
        userRepository.save(user);

        return ResponseEntity.ok("Notifications updated");
    }
}
