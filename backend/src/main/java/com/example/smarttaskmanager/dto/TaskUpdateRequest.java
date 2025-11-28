package com.example.smarttaskmanager.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class TaskUpdateRequest {

    private String title;
    private String description;
    private String status;
    private String priority;
    private LocalDate dueDate;
    private Set<String> tags;
}
