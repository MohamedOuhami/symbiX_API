package com.v01d.symbiX.model;

import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Team
 */
@Document
public class Team {

  @Id
  private String id;

  private String name;

  private String description;

  private Set<String> tags;

  private String ownerId;

  private Set<String> membersIds;

  private Set<String> projectsIds;

  public Team() {
  }

  public Team(String name, String description, Set<String> tags, String ownerId) {
    this.name = name;
    this.description = description;
    this.tags = tags;
    this.ownerId = ownerId;
  }

  public String getDescription() {
    return description;
  }

  public String getName() {
    return name;
  }

  public String getId() {
    return id;
  }

  public String getOwnerId() {
    return ownerId;
  }

  public Set<String> getTags() {
    return tags;
  }

  public Set<String> getMembersIds() {
    return membersIds;
  }

  public Set<String> getProjectsIds() {
    return projectsIds;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setOwnerId(String ownerId) {
    this.ownerId = ownerId;
  }

  public void setTags(Set<String> tags) {
    this.tags = tags;
  }

  public void setMembersIds(Set<String> membersIds) {
    this.membersIds = membersIds;
  }

  public void setProjectsIds(Set<String> projectsIds) {
    this.projectsIds = projectsIds;
  }

}
