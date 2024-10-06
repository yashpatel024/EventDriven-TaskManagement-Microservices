package com.taskmanagement.notificationservice.service;

import com.taskmanagement.notificationservice.model.Notification;
import com.taskmanagement.notificationservice.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

  private final NotificationRepository notificationRepository;

  @Autowired
  public NotificationService(NotificationRepository notificationRepository) {
    this.notificationRepository = notificationRepository;
  }
}