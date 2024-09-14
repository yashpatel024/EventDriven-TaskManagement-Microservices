package com.taskmanager.notification_service.repository;

import com.taskmanager.notification_service.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUserIdAndReadFalse(long userId);
    List<Notification> findByUserIdOrderByCreatedAtDesc(long userId);
}
