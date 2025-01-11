package com.v01d.symbiX.dto;

import java.util.Set;

/**
 * TeamDto
 */
public class TeamDto {

  private String name;
  private String description;
  private Set<String> tags;
  private Long leaderId;
  private Set<Long> membersIds;

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

  public Set<String> getTags() {
    return tags;
  }

  public void setTags(Set<String> tags) {
    this.tags = tags;
  }

  public Long getLeaderId() {
    return leaderId;
  }

  public void setLeaderId(Long leaderId) {
    this.leaderId = leaderId;
  }

  public Set<Long> getMemberIds() {
    return membersIds;
  }

  public void setMemberIds(Set<Long> memberIds) {
    this.membersIds = memberIds;
  }

}
