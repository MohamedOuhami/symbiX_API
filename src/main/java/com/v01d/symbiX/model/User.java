package com.v01d.symbiX.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * User
 */
@Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

  @Id
  private String id;

  private String firstName;

  private String lastName;

  private String email;

  private String profilePic;

  private LocalDate dob;

  private List<String> projectsIds;

  private List<String> teamsIds;

  private List<String> tasksIds;

  private String password;

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setDob(LocalDate dob) {
    this.dob = dob;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setProfilePic(String profilePic) {
    this.profilePic = profilePic;
  }

  public void setProjectsIds(List<String> projectsIds) {
    this.projectsIds = projectsIds;
  }

  public void setTeamsIds(List<String> teamsIds) {
    this.teamsIds = teamsIds;
  }

  public void setTasksIds(List<String> tasksIds) {
    this.tasksIds = tasksIds;
  }

  public List<String> getProjectsIds() {
    return this.projectsIds;
  }

  public List<String> getTeamsIds() {
    return this.teamsIds;
  }

  public List<String> getTasksIds() {
    return this.tasksIds;
  }

}
