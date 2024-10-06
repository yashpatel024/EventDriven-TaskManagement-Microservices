package com.taskmanagement.notificationservice.controller;

import com.taskmanagement.notificationservice.model.Notification;
import com.taskmanagement.notificationservice.model.NotificationType;
import com.taskmanagement.notificationservice.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @GetMapping("/unread/{userId}")
    public ResponseEntity<List<Notification>> getUnreadNotifications(@PathVariable Long userId) {
        return ResponseEntity.ok(notificationService.getUnreadNotifications(userId));
    }

    @GetMapping("/byType/{userId}")
    public ResponseEntity<Map<NotificationType, List<Notification>>> getNotificationsByType(@PathVariable Long userId) {
        List<Notification> notifications = notificationService.getNotificationsByUserId(userId);

        Map<NotificationType, List<Notification>> groupedNotifications = notifications.stream()
                .collect(Collectors.groupingBy(Notification::getType));

        return ResponseEntity.ok(groupedNotifications);
    }

    @PutMapping("/read/{notificationId}")
    public ResponseEntity<Notification> updateNotification(@PathVariable Long notificationId) {
        return ResponseEntity.ok(notificationService.markAsRead(notificationId));
    }
}
