package com.taskmanagement.notificationservice.repository;

import com.taskmanagement.notificationservice.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
  List<Notification> findByUserIdOrderByCreatedAtDesc(Long userId);

  List<Notification> findByUserIdAndReadOrderByCreatedAtDesc(Long userId, boolean read);
}