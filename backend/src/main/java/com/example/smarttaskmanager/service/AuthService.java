package com.example.smarttaskmanager.service;

import com.example.smarttaskmanager.dto.AuthRequest;
import com.example.smarttaskmanager.dto.AuthResponse;
import com.example.smarttaskmanager.dto.RegisterRequest;
import com.example.smarttaskmanager.entity.Role;
import com.example.smarttaskmanager.entity.User;
import com.example.smarttaskmanager.repository.UserRepository;
import com.example.smarttaskmanager.security.JwtService;   // <-- IMPORTANT IMPORT

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;   // <-- field is now resolved by the import above

    // ------------------------------
    // REGISTER USER
    // ------------------------------
    public void register(RegisterRequest req) {

        User user = User.builder()
                .fullName(req.getFullName())
                .email(req.getEmail())
                .password(passwordEncoder.encode(req.getPassword()))
                .enabled(true)
                .notifyEnabled(true)
                .roles(Set.of(Role.EMPLOYEE))   // Set<Role>, matches entity definition
                .build();

        userRepository.save(user);
    }

    // ------------------------------
    // LOGIN USER
    // ------------------------------
    public AuthResponse authenticate(AuthRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        // Convert role enums → strings
        List<String> userRoles = user.getRoles()
                .stream()
                .map(Enum::name)
                .toList();

        String token = jwtService.generateToken(user.getEmail(), userRoles);

        return new AuthResponse(
                token,
                userRoles.toArray(new String[0])
        );
    }

    // ------------------------------
    // FORGOT PASSWORD
    // ------------------------------
    public void sendResetLink(String email) {
        // TODO: implement if needed
    }

    // ------------------------------
    // RESET PASSWORD
    // ------------------------------
    public void resetPassword(String token, String newPassword) {
        // TODO: implement logic
    }
}
