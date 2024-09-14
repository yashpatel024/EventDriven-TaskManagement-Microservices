package com.taskmanager.notification_service.service;

import com.taskmanager.notification_service.model.Notification;
import com.taskmanager.notification_service.model.NotificationType;
import com.taskmanager.notification_service.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    /**
     * Create Notification Entity and Send real time notification to User
     * @param userId
     * @param notificationType
     * @param args
     * @return
     */
    public Notification createNotification(Long userId, NotificationType notificationType, String ...args) {
        String message = String.format(notificationType.getMessageTemplate(), args);

        Notification notification = new Notification();
        notification.setUserId(userId);
        notification.setMessage(message);
        notification.setType(notificationType);
        notification.setRead(false);
        notification.setCreatedAt(LocalDateTime.now());

        Notification savedNotification = notificationRepository.save(notification);

        // Send Real-Time notification via WebSocket
        messagingTemplate.convertAndSendToUser(
                userId.toString(),
                "/queue/notifications",
                savedNotification
        );

        return savedNotification;
    }

    /**
     * Get all unread Message for the User
     * @param userId
     * @return
     */
    public List<Notification> getUnreadNotifications(Long userId) {
        return notificationRepository.findByUserIdAndReadFalse(userId);
    }

    /**
     * Mark the Notification as Read
     * @param notificationId
     * @return
     */
    public Notification markAsRead(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification not found"));

        notification.setRead(true);
        notification.setUpdatedTime(LocalDateTime.now());
        notificationRepository.save(notification);

        return notification;
    }

    public List<Notification> getNotificationsByUserId(Long userId) {
        return notificationRepository.findByUserIdOrderByCreatedAtDesc(userId);
    }
}
