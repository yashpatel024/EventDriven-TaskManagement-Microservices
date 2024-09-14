package com.taskmanager.task_service.service;

import com.google.gson.Gson;
import com.taskmanager.task_service.model.Task;
import com.taskmanager.task_service.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private Gson gson;

    public Task createTask(Task task) {
       Task savedTask = taskRepository.save(task);
//       kafkaTemplate.send("task-created", gson.toJson(savedTask));
       return savedTask;
    }

    public Task getTask(Long id) {
        return taskRepository.findById(id).get();
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
}
