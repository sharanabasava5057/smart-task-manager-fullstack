package com.example.smarttaskmanager.repository;

import com.example.smarttaskmanager.entity.ActivityLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityLogRepository extends JpaRepository<ActivityLog, Long> {
}
