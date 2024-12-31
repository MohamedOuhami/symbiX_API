package com.v01d.symbiX.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.v01d.symbiX.model.Project;
import com.v01d.symbiX.model.Project;
import com.v01d.symbiX.model.User;
import com.v01d.symbiX.repository.ProjectRepository;
import com.v01d.symbiX.service.ProjectService;

/**
 * ProjectController
 */
@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {

  @Autowired
  private ProjectService projectService;

  @GetMapping
  public ResponseEntity<List<Project>> getAllprojects() {

    return ResponseEntity.ok(projectService.getAllProjects());
  }

  // Get project by projectId
  @GetMapping("/{projectId}")
  public ResponseEntity<Project> getprojectById(@PathVariable String projectId) throws Exception{
    return ResponseEntity.ok(projectService.getProjectById(projectId));
  }

  // Create a new Project
  @PostMapping
  public ResponseEntity<Project> createproject(@RequestBody Project project) {
    return ResponseEntity.ok(projectService.createProject(project));
  }

  // Edit a project
  @PutMapping("/{projectId}")
  public ResponseEntity<Project> editproject(@RequestBody Project project, @PathVariable String projectId) {
    try {
      Project originalProject = projectService.getProjectById(projectId);
      project.setId(originalProject.getId());

      return ResponseEntity.ok(projectService.createProject(project));
    } catch (Exception e) {
      System.err.println("The project cannot be updated due to error : " + e.getMessage());

      return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
    }
  }

  // Delete a project
  @DeleteMapping("/{projectId}")
  public ResponseEntity<String> deleteProject(@PathVariable String projectId) {
    try {
      projectService.deleteProject(projectId);
      return ResponseEntity.status(HttpStatus.OK).body("Project deleted successfully");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.CONFLICT).body("Project could not be deleted due to " + e.getMessage());
    }
  }

  // Get the project members
  @GetMapping("/members/{projectId}")
  public ResponseEntity<List<User>> getProjectMembers(@PathVariable String projectId) {

    try {
      List<User> projectMembers = projectService.getProjectMembers(projectId);
      return ResponseEntity.status(HttpStatus.OK).body(projectMembers);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
    }
  }

  @GetMapping("/tasks/{projectId}")
  public ResponseEntity<List<Project>> getProjectProjects(@PathVariable String projectId) {

    try {
      List<Project> projectProjects = projectService.getProjectTasks(projectId);
      return ResponseEntity.status(HttpStatus.OK).body(projectProjects);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
    }
  }

  // Assign new members
  @PutMapping("{projectId}/assignMembers")
  public ResponseEntity<Project> assignMember(@RequestBody List<String> membersIds,@PathVariable String projectId) throws Exception{
    return ResponseEntity.ok(projectService.assignMembers(membersIds, projectId));
  }


  // Assign new members
  @PutMapping("{projectId}/assignTeams")
  public ResponseEntity<Project> assignTeam(@RequestBody List<String> teamsIds,@PathVariable String projectId) throws Exception{
    return ResponseEntity.ok(projectService.assignTeams(teamsIds, projectId));
  }

}

