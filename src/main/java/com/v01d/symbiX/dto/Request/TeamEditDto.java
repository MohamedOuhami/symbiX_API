package com.v01d.symbiX.dto.Request;

import java.util.Set;

/**
 * TeamEditDto
 */
public class TeamEditDto {

  private String name;

  private String description;

  private Set<String> tags;

  public TeamEditDto(String name, String description, Set<String> tags) {
    this.name = name;
    this.description = description;
    this.tags = tags;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public Set<String> getTags() {
    return tags;
  }

}
