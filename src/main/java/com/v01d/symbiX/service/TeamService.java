package com.v01d.symbiX.service;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.v01d.symbiX.dto.Request.AssignMemberToTeamRequest;
import com.v01d.symbiX.dto.Request.TeamDto;
import com.v01d.symbiX.dto.Request.TeamEditDto;
import com.v01d.symbiX.model.Team;
import com.v01d.symbiX.model.User;
import com.v01d.symbiX.repository.TeamRepository;
import com.v01d.symbiX.repository.UserRepository;

import jakarta.persistence.PersistenceException;

/**
 * TeamService
 */
@Service
public class TeamService {

  // Dependency injection
  @Autowired
  private TeamRepository teamRepository;

  @Autowired
  private UserRepository userRepository;

  // Get all the teams
  public List<Team> getAllTeams() {
    return teamRepository.findAll();
  }

  // Get the team by Its id
  public Team getTeamById(Long id) throws Exception {
    Optional<Team> optTeam = teamRepository.findById(id);
    if (optTeam.isPresent()) {
      return optTeam.get();
    } else {
      throw new Exception("Could not find the team of id " + id);
    }
  }

  // Delete Team by Id
  public String deleteTeamById(Long id) {
    try {
      teamRepository.deleteById(id);
      return "Team was deleted succesfully";
    } catch (Exception ex) {
      return "Error deleting the team : " + ex.getMessage();
    }
  }

  // Create a team
  public Team createTeam(TeamDto teamDto) {

    Team newTeam = new Team(teamDto.getName(), teamDto.getDescription(), teamDto.getTags());

    User leader = userRepository.findById(teamDto.getLeaderId()).get();

    Set<User> leaderSet = new HashSet<>();

    leaderSet.add(leader);
    newTeam.setLeader(leader);
    newTeam.setMembers(leaderSet);

    return teamRepository.save(newTeam);
  }

  // Edit a team
  public Team editTeam(Long id, TeamEditDto teamDto) throws Exception {

    // FInd the team by id
    Optional<Team> optTeam = teamRepository.findById(id);

    if (optTeam.isPresent()) {
      Team foundTeam = optTeam.get();

      foundTeam.setName(teamDto.getName());
      foundTeam.setDescription(teamDto.getDescription());
      foundTeam.setTags(teamDto.getTags());

      // Save the new team info
      return teamRepository.save(foundTeam);

    } else {
      throw new Exception("Could not find the team of id " + id);
    }
  }

public String assignMembers(Long teamId, AssignMemberToTeamRequest assignMembersRequest) {
    // Get the team to modify
    Optional<Team> optTeam = teamRepository.findById(teamId);

    // Check if the team exists
    if (optTeam.isEmpty()) {
      return "Could not find the team of id " + teamId;
    }

    Team foundTeam = optTeam.get();
    System.out.println(foundTeam);

    return "Works fine until now";
}


}
