package com.taskmanager.notification_service.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taskmanager.notification_service.model.Notification;
import com.taskmanager.notification_service.model.NotificationType;
import com.taskmanager.notification_service.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
    @Autowired
    private NotificationService notificationService;

    @Autowired
    private ObjectMapper objectMapper;

        @KafkaListener(topics = {"user-created", "task-created", "task-updated", "task-deleted", "task-completed", "task-deadline"},
                groupId = "notification-group")
        public void consume(String message) {
            System.out.println("Message = " + message);
            // parse the message
            JsonNode jsonNode = null;
            try {
                jsonNode = objectMapper.readTree(message);

                String eventType = jsonNode.get("event-type").asText();
                String userId = jsonNode.get("userId").asText();

                NotificationType notificationType = mapEventNotificationType(eventType);
                String[] args = extractArgsFromEvent(jsonNode, eventType);
                notificationService.createNotification(Long.valueOf(userId), notificationType, args);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }

    private NotificationType mapEventNotificationType(String eventType) {
        switch (eventType) {
            case "user-created":
                return NotificationType.USER_CREATED;
            case "task-created":
                return NotificationType.TASK_CREATED;
            case "task-updated":
                return NotificationType.TASK_UPDATED;
            case "task-assigned":
                return NotificationType.TASK_DELETED;
            case "task-completed":
                return NotificationType.TASK_COMPLETED;
            case "task-deadline":
                return NotificationType.TASK_DEADLINE_APPROACHING;
            default:
                throw new IllegalArgumentException("Unknown event type: " + eventType);
        }
    }

    private String[] extractArgsFromEvent(JsonNode data, String eventType) {
        switch (eventType) {
            case "user-created":
                return new String[]{};
            case "task-created":
            case "task-updated":
            case "task-assigned":
            case "task-completed":
                return new String[]{data.get("task").get("title").asText()};
            default:
                return new String[]{};
        }
    }

//    private Notification createNotificationFromMessage(String message) {
//        // Logic to create a Notification object based on the Kafka message
//        // This will vary depending on the message type (user-created, task-created, task-updated)
//    }
}
