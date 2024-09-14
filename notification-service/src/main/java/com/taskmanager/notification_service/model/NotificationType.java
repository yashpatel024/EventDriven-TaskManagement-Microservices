package com.taskmanager.notification_service.model;

public enum NotificationType {
    USER_CREATED("Welcome to the Task Management System!"),
    TASK_CREATED("New task created: %s"),
    TASK_UPDATED("Task updated: %s"),
    TASK_DELETED("Task deleted: %s"),
    TASK_COMPLETED("Task completed: %s"),
    TASK_DEADLINE_APPROACHING("Deadline approaching for task: %s");

    private final String messageTemplate;

    NotificationType(String messageTemplate) {
        this.messageTemplate = messageTemplate;
    }

    public String getMessageTemplate() {
        return messageTemplate;
    }
}
