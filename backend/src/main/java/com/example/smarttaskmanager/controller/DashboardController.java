package com.example.smarttaskmanager.controller;

import com.example.smarttaskmanager.repository.TaskRepository;
import com.example.smarttaskmanager.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/status-count")
    public Map<String, Long> getStatusCounts() {
        return dashboardService.getStatusCounts();
    }

    @GetMapping("/employee-completion")
    public List<TaskRepository.EmployeeCompletion> getEmployeeCompletion() {
        return dashboardService.getEmployeeCompletion();
    }
}
