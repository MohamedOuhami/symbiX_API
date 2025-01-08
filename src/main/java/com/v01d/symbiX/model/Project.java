package com.v01d.symbiX.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

/**
 * Project
 */
@Entity
public class Project {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private String description;

  private LocalDate startDate;

  private LocalDate dueDate;

  private LocalDate endDate;

  @ManyToOne
  @JoinColumn(name = "owner_id")
  private User owner;

  @ManyToMany(mappedBy = "projects")
  private Set<User> collaborators;

  @ManyToMany(mappedBy = "projects")
  private Set<Team> teams;

  @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Task> tasks;

  public Project(String name, String description, LocalDate startDate, LocalDate dueDate, LocalDate endDate,
      User owner, Set<Team> teams) {

    this.name = name;

    this.description = description;
    this.startDate = startDate;

    this.dueDate = dueDate;

    this.endDate = endDate;

  }

  public Project() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public LocalDate getDueDate() {
    return dueDate;
  }

  public void setDueDate(LocalDate dueDate) {
    this.dueDate = dueDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  public User getOwner() {
    return owner;
  }

  public void setOwner(User owner) {
    this.owner = owner;
  }

  public Set<User> getCollaborators() {
    return collaborators;
  }

  public void setCollaborators(Set<User> collaborators) {
    this.collaborators = collaborators;
  }

  public Set<Team> getTeams() {
    return teams;
  }

  public void setTeams(Set<Team> teams) {
    this.teams = teams;
  }

  public Set<Task> getTasks() {
    return tasks;
  }

  public void setTasks(Set<Task> tasks) {
    this.tasks = tasks;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

}
