package com.example.smarttaskmanager.service;

import com.example.smarttaskmanager.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final TaskRepository taskRepository;

    public Map<String, Long> getStatusCounts() {
        long todo = taskRepository.findByStatus(com.example.smarttaskmanager.entity.TaskStatus.TODO).size();
        long progress = taskRepository.findByStatus(com.example.smarttaskmanager.entity.TaskStatus.IN_PROGRESS).size();
        long completed = taskRepository.findByStatus(com.example.smarttaskmanager.entity.TaskStatus.COMPLETED).size();

        return Map.of(
                "todo", todo,
                "inProgress", progress,
                "completed", completed
        );
    }

    public List<TaskRepository.EmployeeCompletion> getEmployeeCompletion() {
        return taskRepository.getCompletedTasksByEmployee();
    }
}
