package com.taskmanagement.projectservice.controller;

import com.taskmanagement.projectservice.model.Project;
import com.taskmanagement.projectservice.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

  private final ProjectService projectService;

  @Autowired
  public ProjectController(ProjectService projectService) {
    this.projectService = projectService;
  }

  @PostMapping
  public ResponseEntity<Project> createProject(@RequestBody Project project) {
    Project createdProject = projectService.createProject(project);
    return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Project> getProjectById(@PathVariable Long id) {
    return projectService.getProjectById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @GetMapping
  public ResponseEntity<List<Project>> getAllProjects() {
    List<Project> projects = projectService.getAllProjects();
    return ResponseEntity.ok(projects);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Project> updateProject(@PathVariable Long id, @RequestBody Project project) {
    project.setId(id);
    Project updatedProject = projectService.updateProject(project);
    return ResponseEntity.ok(updatedProject);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
    projectService.deleteProject(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/user/{userId}")
  public ResponseEntity<List<Project>> getProjectsByUser(@PathVariable Long userId) {
    List<Project> projects = projectService.getProjectsByUser(userId);
    return ResponseEntity.ok(projects);
  }
}