package com.example.smarttaskmanager.controller;

import com.example.smarttaskmanager.dto.TaskCreateRequest;
import com.example.smarttaskmanager.dto.TaskUpdateRequest;
import com.example.smarttaskmanager.entity.Task;
import com.example.smarttaskmanager.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    // CREATE TASK (NO EXTRA PARAMETER)
    @PostMapping
    public Task createTask(@RequestBody TaskCreateRequest req) {
        return taskService.createTask(req);
    }

    // UPDATE TASK
    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id,
                           @RequestBody TaskUpdateRequest req) {
        return taskService.updateTask(id, req);
    }

    // GET TASKS FOR USER
    @GetMapping("/my/{email}")
    public List<Task> getMyTasks(@PathVariable String email) {
        return taskService.getMyTasks(email);
    }

    // GET TASKS BY STATUS
    @GetMapping("/status/{status}")
    public List<Task> getByStatus(@PathVariable String status) {
        return taskService.getByStatus(status);
    }
}
