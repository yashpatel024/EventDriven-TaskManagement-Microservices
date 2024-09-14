package com.taskmanager.task_service.controller;

import com.taskmanager.task_service.model.Task;
import com.taskmanager.task_service.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping("/create")
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        return ResponseEntity.ok(taskService.createTask(task));
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getTasks(@PathVariable Long taskId) {
        Task task = taskService.getTask(taskId);
        if (task != null) {
            return ResponseEntity.ok(task);
        }

        return ResponseEntity.notFound().build();
    }
}
