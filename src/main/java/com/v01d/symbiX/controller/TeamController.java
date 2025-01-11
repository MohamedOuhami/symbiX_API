package com.v01d.symbiX.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.v01d.symbiX.dto.TeamDto;
import com.v01d.symbiX.dto.TeamEditDto;
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

  // Get all the teams
  @GetMapping
  public ResponseEntity<List<Team>> getAllTeams(){
    return ResponseEntity.ok(teamService.getAllTeams());
  }

  // Get the team by its id
  @GetMapping("/{id}")
  public ResponseEntity<Team> getTeamById(@PathVariable Long id) throws Exception{
    return ResponseEntity.ok(teamService.getTeamById(id));
  }

  // Create a new Team
  @PostMapping
  public ResponseEntity<Team> createTeam(@RequestBody TeamDto team){

    System.out.println("Entered the post create endpoint");
    return ResponseEntity.ok(teamService.createTeam(team));
  }

  // Delete the team
  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteTeamById(@PathVariable Long id){
    return ResponseEntity.ok(teamService.deleteTeamById(id));

  }

  // Edit a team's info
  @PutMapping("/{id}")
  public ResponseEntity<Team> editTeam(@PathVariable Long id, @RequestBody TeamEditDto teamDto) throws Exception{
    return ResponseEntity.ok(teamService.editTeam(id,teamDto));
  }

  
}
