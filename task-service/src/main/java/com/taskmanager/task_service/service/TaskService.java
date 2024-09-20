package com.taskmanager.task_service.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taskmanager.task_service.model.Task;
import com.taskmanager.task_service.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public Task createTask(Task task) {
        final String event = "task-created";
        Map<String, Object> kafkaMessage = new HashMap<>();
        Task savedTask = null;

        try {
           savedTask = taskRepository.save(task);

           kafkaMessage.put("event-type", event);
            kafkaMessage.put("userId", savedTask.getUserId());
           kafkaMessage.put("task", savedTask);

           kafkaTemplate.send(event, objectMapper.writeValueAsString(kafkaMessage));
        } catch (Exception e) {
           System.err.println(e.getMessage());
        }

        return savedTask;
    }

    public Task getTaskById(Long id) {
        // Optional<T> uses when there is possibility of no result
        Optional<Task> requestTask = taskRepository.findById(id);
        return requestTask.orElse(null);
    }

    public List<Task> getAllTasksByUser(Long userId) {
        return taskRepository.findUserById(userId);
    }
}
