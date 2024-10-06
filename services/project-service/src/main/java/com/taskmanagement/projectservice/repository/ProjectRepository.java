package com.taskmanagement.projectservice.repository;

import com.taskmanagement.projectservice.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
  List<Project> findByCreatedBy(Long userId);
}