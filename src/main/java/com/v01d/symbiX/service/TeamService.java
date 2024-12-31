package com.v01d.symbiX.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.v01d.symbiX.model.Project;
import com.v01d.symbiX.model.Team;
import com.v01d.symbiX.model.User;
import com.v01d.symbiX.repository.ProjectRepository;
import com.v01d.symbiX.repository.TeamRepository;
import com.v01d.symbiX.repository.UserRepository;

/**
 * TeamService
 */
@Service
public class TeamService {

  @Autowired
  private TeamRepository teamRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ProjectRepository projectRepository;

  // Create new Team
  public Team createTeam(Team team) {

    return teamRepository.save(team);
  }

  // Edit a team
  public Team editTeam(String teamId, Team team) throws Exception {
    Optional<Team> existingTeam = teamRepository.findById(teamId);

    if (existingTeam.isPresent()) {
      team.setId(teamId);
      return team;
    } else {
      throw new Exception("The Team cannot be found of id " + teamId);
    }
  }

  // Get all the teams
  public List<Team> getAllTeams() {
    return teamRepository.findAll();
  }

  // Get the team by ID
  public Team getTeamById(String id) throws Exception {
    Optional<Team> existingTeam = teamRepository.findById(id);

    if (existingTeam.isPresent()) {
      return existingTeam.get();
    } else {
      throw new Exception("Cannot find the team of id " + id);
    }
  }

  // Get the team members
  public List<User> getTeamMembers(String teamId) throws Exception {
    Optional<Team> existingTeam = teamRepository.findById(teamId);

    if (existingTeam.isPresent()) {
      Team team = existingTeam.get();

      Set<String> membersIds = team.getMembersIds();
      System.out.println("These are the members Ids " + membersIds);

      List<User> members = userRepository.findAllById(membersIds);

      System.out.println("And these are the members Objects " + members);

      return members;
    } else {
      throw new Exception("Cannot find the member of the team : " + teamId);
    }
  }

  // Get the team projects
  public List<Project> getTeamProjects(String teamId) throws Exception {
    Optional<Team> existingTeam = teamRepository.findById(teamId);

    if (existingTeam.isPresent()) {
      Team team = existingTeam.get();

      Set<String> projectsIds = team.getProjectsIds();
      System.out.println("These are the members Ids " + projectsIds);

      List<Project> projects = projectRepository.findAllById(projectsIds);

      System.out.println("And these are the members Objects " + projects);

      return projects;
    } else {
      throw new Exception("Cannot find the member of the team : " + teamId);
    }
  }

  // Assign new members
   
  public Team assignMembers(List<String> membersIds, String teamId) throws Exception {

    Optional<Team> existingTeam = teamRepository.findById(teamId);

    if (existingTeam.isPresent()) {
      Team team = existingTeam.get();

      Set<String> originalMembersIds = team.getMembersIds();
      System.out.println("These are the members Ids " + membersIds);

      originalMembersIds.addAll(membersIds);

      team.setMembersIds(originalMembersIds);

      // Set the projectList of the users

      List<User> newMembers = userRepository.findAllById(originalMembersIds);

      for (User member : newMembers) {

        List<String> memberTeams = member.getTeamsIds();

        if(memberTeams == null){
          memberTeams = new ArrayList<>();
        }

        memberTeams.add(team.getId());

        member.setTeamsIds(memberTeams);

        userRepository.save(member);
        teamRepository.save(team);
      }

      return team;
    } else {
      throw new Exception("Cannot find the member of the project : " + teamId);
    }
  }


  // Delete the team By ID
  public String deleteTeam(String teamId) {
    try {
      teamRepository.deleteById(teamId);
      return "The team was succesfully deleted";
    } catch (Exception e) {
      return "The team was not deleted, error : " + e.getMessage();
    }
  }

}
