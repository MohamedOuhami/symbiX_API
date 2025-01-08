package com.v01d.symbiX.model;

import java.time.LocalDate;
import java.util.Set;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

/**
 * Task
 */
@Entity
public class Task {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private String description;

  private LocalDate startDate;

  private LocalDate dueDate;

  private LocalDate endDate;

  private String currentState;

  @ManyToMany(mappedBy = "tasks")
  private Set<User> collaborators;

  @ManyToOne(fetch=FetchType.LAZY)
  @JoinColumn(name = "project_id", nullable = false)
  private Project project;

  public Task() {
  }

  public Task(String name, String description, LocalDate startDate, LocalDate dueDate,
      Project project) {

    this.name = name;
    this.description = description;

    this.startDate = startDate;

    this.dueDate = dueDate;

    this.project = project;
  }

  public String getName() {
    return name;
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

  public String getCurrentState() {
    return currentState;
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

  public void setCurrentState(String currentState) {
    this.currentState = currentState;
  }

  public Set<User> getCollaborators() {
    return collaborators;
  }

  public void setCollaborators(Set<User> collaborators) {
    this.collaborators = collaborators;
  }

  public Project getProject() {
    return project;
  }

  public void setProject(Project project) {
    this.project = project;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

}
