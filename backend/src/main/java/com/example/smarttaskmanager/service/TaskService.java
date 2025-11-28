package com.example.smarttaskmanager.service;

import com.example.smarttaskmanager.dto.TaskCreateRequest;
import com.example.smarttaskmanager.dto.TaskUpdateRequest;
import com.example.smarttaskmanager.entity.Task;
import com.example.smarttaskmanager.entity.TaskPriority;
import com.example.smarttaskmanager.entity.TaskStatus;
import com.example.smarttaskmanager.entity.User;
import com.example.smarttaskmanager.repository.TaskRepository;
import com.example.smarttaskmanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    // --------------------------
    // CREATE TASK
    // --------------------------
    public Task createTask(TaskCreateRequest req) {

        User creator = userRepository.findByEmail(req.getCreatedByEmail())
                .orElseThrow(() -> new RuntimeException("Creator not found"));

        User assignee = userRepository.findByEmail(req.getAssigneeEmail())
                .orElseThrow(() -> new RuntimeException("Assignee not found"));

        Task task = Task.builder()
                .title(req.getTitle())
                .description(req.getDescription())
                .dueDate(req.getDueDate())
                .priority(TaskPriority.valueOf(req.getPriority().toUpperCase()))
                .status(TaskStatus.valueOf(req.getStatus().toUpperCase()))
                .createdBy(creator)
                .assignedTo(assignee)
                .tags(req.getTags() != null ? Set.copyOf(req.getTags()) : Set.of())
                .build();

        return taskRepository.save(task);
    }

    // --------------------------
    // UPDATE TASK
    // --------------------------
    public Task updateTask(Long id, TaskUpdateRequest req) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        if (req.getTitle() != null) task.setTitle(req.getTitle());
        if (req.getDescription() != null) task.setDescription(req.getDescription());
        if (req.getDueDate() != null) task.setDueDate(req.getDueDate());

        if (req.getPriority() != null)
            task.setPriority(TaskPriority.valueOf(req.getPriority().toUpperCase()));

        if (req.getStatus() != null)
            task.setStatus(TaskStatus.valueOf(req.getStatus().toUpperCase()));

        return taskRepository.save(task);
    }

    // --------------------------
    // GET TASKS ASSIGNED TO USER
    // --------------------------
    public List<Task> getMyTasks(String email) {
        return taskRepository.findByAssignedToEmail(email);
    }

    // --------------------------
    // GET TASKS BY STATUS
    // --------------------------
    public List<Task> getByStatus(String status) {

        TaskStatus sts;
        try {
            sts = TaskStatus.valueOf(status.toUpperCase());
        } catch (Exception ex) {
            throw new RuntimeException("Invalid task status: " + status);
        }

        return taskRepository.findByStatus(sts);
    }
}
