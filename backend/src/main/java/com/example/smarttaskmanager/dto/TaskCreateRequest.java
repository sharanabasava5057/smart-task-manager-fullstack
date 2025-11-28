package com.example.smarttaskmanager.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class TaskCreateRequest {

    private String title;
    private String description;
    private LocalDate dueDate;

    // ENUMS, not Strings
    private String priority;   // TaskPriority enum name
    private String status;     // TaskStatus enum name

    private String assigneeEmail;     // The person assigned
    private String createdByEmail;    // The person who created the task

    private List<String> tags;        // Tags like ["urgent", "backend"]
}
