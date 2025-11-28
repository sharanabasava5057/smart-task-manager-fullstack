package com.example.smarttaskmanager.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "activity_logs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActivityLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // e.g. "Created user john@example.com", "Deleted task #5"
    private String action;

    // Email or name of the admin/actor who performed the action
    private String performedBy;

    private LocalDateTime timestamp;
}
