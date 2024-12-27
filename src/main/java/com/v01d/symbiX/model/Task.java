package com.v01d.symbiX.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Task
 */
@Document
public class Task {

  @Id
  private String id;

  private String name;

  private String description;

  private LocalDate startDate;

  private LocalDate dueDate;

  private LocalDate endDate;

  private String currentState;

  private String leaderId;

  private Set<String> collaboratorsIds;

  private String projectId;

  public Task() {
  }

  public Task(String name, String description, LocalDate startDate, LocalDate dueDate, String leaderId,
      String projectId) {
    this.name = name;
    this.description = description;

    this.startDate = startDate;

    this.dueDate = dueDate;

    this.leaderId = leaderId;

    this.projectId = projectId;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Set<String> getCollaboratorsIds() {
    return collaboratorsIds;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public String getDescription() {
    return description;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public LocalDate getDueDate() {
    return dueDate;
  }

  public String getLeaderId() {
    return leaderId;
  }

  public String getProjectId() {
    return projectId;
  }

  public String getCurrentState() {
    return currentState;
  }

  public void setCollaboratorsIds(Set<String> collaboratorsIds) {
    this.collaboratorsIds = collaboratorsIds;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  public void setDueDate(LocalDate dueDate) {
    this.dueDate = dueDate;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setLeaderId(String leaderId) {
    this.leaderId = leaderId;
  }

  public void setProjectId(String projectId) {
    this.projectId = projectId;
  }

  public void setCurrentState(String currentState) {
    this.currentState = currentState;
  }

}
