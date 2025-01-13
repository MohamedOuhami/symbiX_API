package com.v01d.symbiX.dto.Request;

import java.util.Set;

import lombok.Data;

/**
 * TeamDto
 */
@Data
public class TeamDto {

  private String name;
  private String description;
  private Set<String> tags;
  private Long leaderId;
}
