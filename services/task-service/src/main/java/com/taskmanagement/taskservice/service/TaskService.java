package com.taskmanagement.taskservice.service;

import com.taskmanagement.taskservice.model.Task;
import com.taskmanagement.taskservice.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

  private final TaskRepository taskRepository;

  @Autowired
  public TaskService(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  public Task createTask(Task task) {
    return taskRepository.save(task);
  }

  public Optional<Task> getTaskById(Long id) {
    return taskRepository.findById(id);
  }

  public List<Task> getAllTasks() {
    return taskRepository.findAll();
  }

  public Task updateTask(Task task) {
    return taskRepository.save(task);
  }

  public void deleteTask(Long id) {
    taskRepository.deleteById(id);
  }

  public List<Task> getTasksByAssignedUser(Long userId) {
    return taskRepository.findByAssignedTo(userId);
  }

  public List<Task> getTasksByProject(Long projectId) {
    return taskRepository.findByProjectId(projectId);
  }
}