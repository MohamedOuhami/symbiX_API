package com.v01d.symbiX.model;

import java.util.Set;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

/**
 * Team
 */
@Entity
public class Team {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private String description;

  private Set<String> tags;

  @ManyToOne
  @JoinColumn(name = "owner_id", nullable = false)
  private User owner;

  @ManyToMany(mappedBy = "teams")
  private Set<User> members;

  @ManyToMany
  @JoinTable(name = "teams_projects", joinColumns = @JoinColumn(name = "team_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id"))
  private Set<Project> projects;

  public Team() {
  }

  public Team(String name, String description, Set<String> tags, User owner) {
    this.name = name;
    this.description = description;
    this.tags = tags;
    this.owner = owner;
  }

  public String getDescription() {
    return description;
  }

  public String getName() {
    return name;
  }

  public Set<String> getTags() {
    return tags;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setTags(Set<String> tags) {
    this.tags = tags;
  }

  public User getOwner() {
    return owner;
  }

  public void setOwner(User owner) {
    this.owner = owner;
  }

  public Set<User> getMembers() {
    return members;
  }

  public void setMembers(Set<User> members) {
    this.members = members;
  }

  public Set<Project> getProjects() {
    return projects;
  }

  public void setProjects(Set<Project> projects) {
    this.projects = projects;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

}
