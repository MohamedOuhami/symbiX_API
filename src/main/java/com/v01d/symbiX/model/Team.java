package com.v01d.symbiX.model;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Team
 */
@Entity
@Data
@NoArgsConstructor
public class Team {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private String description;

  @ManyToOne
  @JoinColumn(name = "leader_id")
  private User leader;

  @ManyToMany(fetch = FetchType.LAZY)
  private Set<User> members;

  private Set<String> tags;

  public Team(String name, String description, Set<String> tags) {
    this.name = name;
    this.description = description;
    this.tags = tags;
  }

}
