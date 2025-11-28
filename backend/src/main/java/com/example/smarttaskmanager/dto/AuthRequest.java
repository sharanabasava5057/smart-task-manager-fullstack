package com.example.smarttaskmanager.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String email;     // MUST MATCH ANGULAR
    private String password;
}
