package com.v01d.symbiX.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.v01d.symbiX.dto.Request.AssignMemberToTeamRequest;
import com.v01d.symbiX.dto.Request.TeamDto;
import com.v01d.symbiX.dto.Request.TeamEditDto;
import com.v01d.symbiX.model.Team;
import com.v01d.symbiX.service.TeamService;

/**
 * TeamController
 */
@RestController
@RequestMapping("/api/v1/teams")
public class TeamController {

  // Dependencies Injection
  @Autowired
  private TeamService teamService;
  private static final Logger logger = LoggerFactory.getLogger(TeamController.class);

  // Get all the teams
  @GetMapping
  public ResponseEntity<List<Team>> getAllTeams() {
    return ResponseEntity.ok(teamService.getAllTeams());
  }

  // Get the team by its id
  @GetMapping("/{id}")
  public ResponseEntity<Team> getTeamById(@PathVariable Long id) throws Exception {
    return ResponseEntity.ok(teamService.getTeamById(id));
  }

  // Create a new Team
  @PostMapping
  public ResponseEntity<Team> createTeam(@RequestBody TeamDto team) {

    System.out.println("Entered the post create endpoint");
    return ResponseEntity.ok(teamService.createTeam(team));
  }

  // Delete the team
  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteTeamById(@PathVariable Long id) {
    return ResponseEntity.ok(teamService.deleteTeamById(id));

  }

  // Edit a team's info
  @PutMapping("/{id}")
  public ResponseEntity<Team> editTeam(@PathVariable Long id, @RequestBody TeamEditDto teamDto) throws Exception {
    return ResponseEntity.ok(teamService.editTeam(id, teamDto));
  }

  // Assign a new member to the team
  @PostMapping("/assignMembers/{team_id}")
  public ResponseEntity<String> assignMembers(@PathVariable Long team_id,
      @RequestBody AssignMemberToTeamRequest membersIds) throws Exception {
    try {
      return ResponseEntity.ok(teamService.assignMembers(team_id, membersIds));
    } catch (NoSuchElementException ex) {
      logger.warn("Team not found: {}", ex.getMessage());
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    } catch (IllegalArgumentException ex) {
      logger.error("Invalid input: {}", ex.getMessage());
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    } catch (Exception ex) {
      logger.error("Unexpected error occurred: {}", ex.getMessage(), ex);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

  }
}
