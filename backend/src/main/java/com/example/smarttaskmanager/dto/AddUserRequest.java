package com.example.smarttaskmanager.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddUserRequest {
    private String fullName;
    private String email;
    private String password;
    private String role;
}
