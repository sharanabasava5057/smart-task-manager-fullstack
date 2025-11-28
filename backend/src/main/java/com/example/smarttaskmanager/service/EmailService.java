package com.example.smarttaskmanager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendEmail(String to, String subject, String message) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(to);
        mail.setSubject(subject);
        mail.setText(message);
        mailSender.send(mail);
    }

    // Verification email
    public void sendVerificationEmail(String to, String token) {
        String verifyLink = "http://localhost:8080/api/auth/verify?token=" + token;

        String message = 
                "Welcome!\n\n" +
                "Please verify your email:\n" +
                verifyLink + "\n\n";

        sendEmail(to, "Verify your account", message);
    }

    // Forgot password email
    public void sendResetEmail(String to, String token) {
        String resetLink = "http://localhost:4200/reset-password?token=" + token;

        String message =
                "Reset your password here:\n" +
                resetLink + "\n\n";

        sendEmail(to, "Password Reset", message);
    }

    // 🔔 NEW — Task Assigned Email
    public void sendTaskAssignedEmail(String to, String taskTitle, String dueDate) {
        String message =
                "A new task has been assigned to you.\n\n" +
                "Task: " + taskTitle + "\n" +
                "Due: " + dueDate + "\n\n" +
                "Login to view your task.";

        sendEmail(to, "New Task Assigned", message);
    }

    // 🔔 NEW — Daily Reminder Email
    public void sendDailySummaryEmail(String to, String summary) {
        String message =
                "Your daily task summary:\n\n" +
                summary +
                "\nHave a productive day!";

        sendEmail(to, "Daily Task Reminder", message);
    }
}
