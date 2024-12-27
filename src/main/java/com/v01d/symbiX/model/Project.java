package com.v01d.symbiX.model;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Project
 */
@Document
public class Project {
  @Id
  private String id;

  private String name;

  private String description;

  private LocalDate startDate;

  private LocalDate dueDate;

  private LocalDate endDate;

  private String ownerId;

  private Set<String> collaboratorsIds;

  private Set<String> teamsIds;

  private Set<String> tasksIds;

  public Project(String name, String description, LocalDate startDate, LocalDate dueDate, LocalDate endDate,
      String ownerId, Set<String> teamsIds) {

    this.name = name;

    this.description = description;
    this.startDate = startDate;

    this.dueDate = dueDate;

    this.endDate = endDate;

    this.ownerId = ownerId;

    this.teamsIds = teamsIds;

  }

  public Project() {
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public LocalDate getDueDate() {
    return dueDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public String getOwnerId() {
    return ownerId;
  }

  public Set<String> getTasksIds() {
    return tasksIds;
  }

  public Set<String> getTeamsIds() {
    return teamsIds;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public String getDescription() {
    return description;
  }

  public Set<String> getCollaboratorsIds() {
    return collaboratorsIds;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setDueDate(LocalDate dueDate) {
    this.dueDate = dueDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  public void setOwnerId(String ownerId) {
    this.ownerId = ownerId;
  }

  public void setTasksIds(Set<String> tasksIds) {
    this.tasksIds = tasksIds;
  }

  public void setTeamsIds(Set<String> teamsIds) {
    this.teamsIds = teamsIds;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setCollaboratorsIds(Set<String> collaboratorsIds) {
    this.collaboratorsIds = collaboratorsIds;
  }
}
