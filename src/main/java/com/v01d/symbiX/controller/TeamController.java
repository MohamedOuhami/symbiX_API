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
import com.v01d.symbiX.model.Team;
import com.v01d.symbiX.model.User;
import com.v01d.symbiX.repository.TeamRepository;
import com.v01d.symbiX.service.TeamService;

/**
 * TeamController
 */
@RestController
@RequestMapping("/api/v1/team")
public class TeamController {

  @Autowired
  private TeamService teamService;

  @GetMapping
  public ResponseEntity<List<Team>> getAllTeams() {

    return ResponseEntity.ok(teamService.getAllTeams());
  }

  // Get team by teamId
  @GetMapping("/{teamId}")
  public ResponseEntity<Team> getTeamById(@PathVariable String teamId) throws Exception {
    return ResponseEntity.ok(teamService.getTeamById(teamId));
  }

  // Create a new Team
  @PostMapping
  public ResponseEntity<Team> createTeam(@RequestBody Team team) {
    return ResponseEntity.ok(teamService.createTeam(team));
  }

  // Edit a team
  @PutMapping("/{teamId}")
  public ResponseEntity<Team> editTeam(@RequestBody Team team, @PathVariable String teamId) {
    try {
      Team originalTeam = teamService.getTeamById(teamId);
      team.setId(originalTeam.getId());

      return ResponseEntity.ok(teamService.createTeam(team));
    } catch (Exception e) {
      System.err.println("The team cannot be updated due to error : " + e.getMessage());

      return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
    }
  }

  // Delete a team
  @DeleteMapping("/{teamId}")
  public ResponseEntity<String> deleteTeam(@PathVariable String teamId) {
    try {
      teamService.deleteTeam(teamId);
      return ResponseEntity.status(HttpStatus.OK).body("Team deleted successfully");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.CONFLICT).body("Team could not be deleted due to " + e.getMessage());
    }
  }

  // Get the team members
  @GetMapping("/members/{teamId}")
  public ResponseEntity<List<User>> getTeamMembers(@PathVariable String teamId) {

    try {
      List<User> teamMembers = teamService.getTeamMembers(teamId);
      return ResponseEntity.status(HttpStatus.OK).body(teamMembers);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
    }
  }

  @GetMapping("/projects/{teamId}")
  public ResponseEntity<List<Project>> getTeamProjects(@PathVariable String teamId) {

    try {
      List<Project> teamProjects = teamService.getTeamProjects(teamId);
      return ResponseEntity.status(HttpStatus.OK).body(teamProjects);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
    }
  }

  // Assign new members
  @PutMapping("{teamId}/assignMembers")
  public ResponseEntity<Team> assignTeam(@RequestBody List<String> membersIds, @PathVariable String teamId)
      throws Exception {
    return ResponseEntity.ok(teamService.assignMembers(membersIds, teamId));
  }

}
