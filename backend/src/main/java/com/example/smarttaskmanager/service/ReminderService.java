package com.example.smarttaskmanager.service;

import com.example.smarttaskmanager.entity.Task;
import com.example.smarttaskmanager.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReminderService {

    private final TaskRepository taskRepository;
    private final EmailService emailService;

    // Runs every day at 9 AM
    @Scheduled(cron = "0 0 9 * * *")
    public void sendDailyReminders() {

        List<Task> tasks = taskRepository.findAll();

        tasks.forEach(t -> {
            if (t.getAssignedTo() != null &&
                t.getAssignedTo().isNotifyEnabled() &&
                t.getDueDate() != null &&
                t.getDueDate().isEqual(LocalDate.now())) {

                emailService.sendEmail(
                        t.getAssignedTo().getEmail(),
                        "Task Due Today",
                        "Your task \"" + t.getTitle() + "\" is due today."
                );
            }
        });
    }
}
