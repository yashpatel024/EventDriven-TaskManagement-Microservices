package com.taskmanagement.projectservice.service;

import com.taskmanagement.projectservice.model.Project;
import com.taskmanagement.projectservice.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

  private final ProjectRepository projectRepository;

  @Autowired
  public ProjectService(ProjectRepository projectRepository) {
    this.projectRepository = projectRepository;
  }

  public Project createProject(Project project) {
    return projectRepository.save(project);
  }

  public Optional<Project> getProjectById(Long id) {
    return projectRepository.findById(id);
  }

  public List<Project> getAllProjects() {
    return projectRepository.findAll();
  }

  public Project updateProject(Project project) {
    return projectRepository.save(project);
  }

  public void deleteProject(Long id) {
    projectRepository.deleteById(id);
  }

  public List<Project> getProjectsByUser(Long userId) {
    return projectRepository.findByCreatedBy(userId);
  }
}