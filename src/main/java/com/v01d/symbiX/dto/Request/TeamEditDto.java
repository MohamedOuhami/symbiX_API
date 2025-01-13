package com.v01d.symbiX.dto.Request;

import java.util.Set;

import lombok.Data;

/**
 * TeamEditDto
 */
@Data
public class TeamEditDto {

  private String name;

  private String description;

  private Set<String> tags;

  public TeamEditDto(String name, String description, Set<String> tags) {
    this.name = name;
    this.description = description;
    this.tags = tags;
  }

}
