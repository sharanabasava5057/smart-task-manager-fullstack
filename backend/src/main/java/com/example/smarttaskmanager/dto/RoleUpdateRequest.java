package com.example.smarttaskmanager.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleUpdateRequest {
    private Long userId;
    private String role;
}
