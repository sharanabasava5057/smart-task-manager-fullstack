package com.example.smarttaskmanager.repository;

import com.example.smarttaskmanager.entity.Task;
import com.example.smarttaskmanager.entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    // For user tasks
    List<Task> findByAssignedToEmail(String email);

    List<Task> findByStatus(TaskStatus status);

    // ================================
    // EMPLOYEE COMPLETION AGGREGATION 
    // ================================
    public interface EmployeeCompletion {
        String getFullName();
        Long getCompletedCount();
    }

    @Query("""
            SELECT t.assignedTo.fullName AS fullName, COUNT(t) AS completedCount
            FROM Task t
            WHERE t.status = 'COMPLETED'
            GROUP BY t.assignedTo.fullName
           """)
    List<EmployeeCompletion> getCompletedTasksByEmployee();
}
