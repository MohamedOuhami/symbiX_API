package com.v01d.symbiX.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.v01d.symbiX.model.Project;
import com.v01d.symbiX.model.Team;
import com.v01d.symbiX.model.User;
import com.v01d.symbiX.repository.ProjectRepository;
import com.v01d.symbiX.repository.ProjectRepository;
import com.v01d.symbiX.repository.TeamRepository;
import com.v01d.symbiX.repository.UserRepository;

/**
 * ProjectService
 */
@Service
public class ProjectService {

  @Autowired
  private ProjectRepository projectRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private TeamRepository teamRepository;

  // Create new Project
  public Project createProject(Project project) {

    return projectRepository.save(project);
  }

  // Edit a project
  public Project editProject(String projectId, Project project) throws Exception {
    Optional<Project> existingProject = projectRepository.findById(projectId);

    if (existingProject.isPresent()) {
      project.setId(projectId);
      return project;
    } else {
      throw new Exception("The Project cannot be found of id " + projectId);
    }
  }

  // Get all the projects
  public List<Project> getAllProjects() {
    return projectRepository.findAll();
  }

  // Get the project by ID
  public Project getProjectById(String id) throws Exception {
    Optional<Project> existingProject = projectRepository.findById(id);

    if (existingProject.isPresent()) {
      return existingProject.get();
    } else {
      throw new Exception("Cannot find the project of id " + id);
    }
  }

  // Get the project members
  public List<User> getProjectMembers(String projectId) throws Exception {
    Optional<Project> existingProject = projectRepository.findById(projectId);

    if (existingProject.isPresent()) {
      Project project = existingProject.get();

      Set<String> membersIds = project.getCollaboratorsIds();
      System.out.println("These are the members Ids " + membersIds);

      List<User> members = userRepository.findAllById(membersIds);

      System.out.println("And these are the members Objects " + members);

      return members;
    } else {
      throw new Exception("Cannot find the member of the project : " + projectId);
    }
  }

  // Assign new members
  public Project assignMembers(List<String> membersIds, String projectId) throws Exception {

    Optional<Project> existingProject = projectRepository.findById(projectId);

    if (existingProject.isPresent()) {
      Project project = existingProject.get();

      Set<String> originalMembersIds = project.getCollaboratorsIds();
      System.out.println("These are the members Ids " + membersIds);

      originalMembersIds.addAll(membersIds);

      project.setCollaboratorsIds(originalMembersIds);

      // Set the projectList of the users

      List<User> newMembers = userRepository.findAllById(originalMembersIds);

      for (User member : newMembers) {

        List<String> memberProjects = member.getProjectsIds();

        if(memberProjects == null){
          memberProjects = new ArrayList<>();
        }

        memberProjects.add(project.getId());

        member.setProjectsIds(memberProjects);

        userRepository.save(member);
        projectRepository.save(project);
      }

      return project;
    } else {
      throw new Exception("Cannot find the member of the project : " + projectId);
    }

  }

  // Assign new members
  public Project assignToTeams(List<String> teamsIds, String projectId) throws Exception {

    Optional<Project> existingProject = projectRepository.findById(projectId);

    if (existingProject.isPresent()) {
      Project project = existingProject.get();

      Set<String> originalTeamsIds = project.getTeamsIds();
      System.out.println("These are the members Ids " + teamsIds);

      // Checking if the originalMembersIds is null
      if (originalTeamsIds == null) {
        originalTeamsIds = new HashSet<>();
      }
      originalTeamsIds.addAll(teamsIds);

      project.setTeamsIds(originalTeamsIds);

      // Set the projectList of the users

      List<Team> newTeams = teamRepository.findAllById(originalTeamsIds);

      for (Team team : newTeams) {

        Set<String> teamProjects = team.getProjectsIds();

        teamProjects.add(project.getId());

        team.setProjectsIds(teamProjects);

        teamRepository.save(team);
        projectRepository.save(project);
      }

      return project;
    } else {
      throw new Exception("Cannot find the member of the project : " + projectId);
    }

  }

  // Get the project tasks
  public List<Project> getProjectTasks(String projectId) throws Exception {
    Optional<Project> existingProject = projectRepository.findById(projectId);

    if (existingProject.isPresent()) {
      Project project = existingProject.get();

      Set<String> tasksIds = project.getTasksIds();
      System.out.println("These are the members Ids " + tasksIds);

      List<Project> projects = projectRepository.findAllById(tasksIds);

      System.out.println("And these are the members Objects " + projects);

      return projects;
    } else {
      throw new Exception("Cannot find the member of the project : " + projectId);
    }
  }

  // Delete the project By ID
  public String deleteProject(String projectId) {
    try {
      projectRepository.deleteById(projectId);
      return "The project was succesfully deleted";
    } catch (Exception e) {
      return "The project was not deleted, error : " + e.getMessage();
    }
  }

}
