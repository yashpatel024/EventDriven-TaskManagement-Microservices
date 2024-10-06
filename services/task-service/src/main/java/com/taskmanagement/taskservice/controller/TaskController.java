package com.taskmanagement.taskservice.controller;

import com.taskmanagement.taskservice.model.Task;
import com.taskmanagement.taskservice.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

  private final TaskService taskService;

  @Autowired
  public TaskController(TaskService taskService) {
    this.taskService = taskService;
  }

  @PostMapping
  public ResponseEntity<Task> createTask(@RequestBody Task task) {
    Task createdTask = taskService.createTask(task);
    return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
    return taskService.getTaskById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @GetMapping
  public ResponseEntity<List<Task>> getAllTasks() {
    List<Task> tasks = taskService.getAllTasks();
    return ResponseEntity.ok(tasks);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {
    task.setId(id);
    Task updatedTask = taskService.updateTask(task);
    return ResponseEntity.ok(updatedTask);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
    taskService.deleteTask(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/user/{userId}")
  public ResponseEntity<List<Task>> getTasksByAssignedUser(@PathVariable Long userId) {
    List<Task> tasks = taskService.getTasksByAssignedUser(userId);
    return ResponseEntity.ok(tasks);
  }

  @GetMapping("/project/{projectId}")
  public ResponseEntity<List<Task>> getTasksByProject(@PathVariable Long projectId) {
    List<Task> tasks = taskService.getTasksByProject(projectId);
    return ResponseEntity.ok(tasks);
  }
}