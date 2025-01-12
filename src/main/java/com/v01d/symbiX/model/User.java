package com.v01d.symbiX.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String firstName;
  private String lastName;

  // The username must be unique and not null
  @Column(nullable = false, unique = true)
  private String username;

  // The email must be unique and not null
  @Column(nullable = false, unique = true)
  private String email;

  // Password must not be null
  @Column(nullable = false)
  private String password;

  private LocalDate dob;

  // Roles of the user
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
    name = "users_roles",
    joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
  )
  private Set<Role> roles;

  // Teams managed by the user
  @OneToMany(mappedBy = "leader", orphanRemoval = true)
  @JsonIgnore
  private List<Team> teamsManaged;

  // Teams the user has joined
  @ManyToMany(mappedBy = "members")
  @JsonIgnore
  private Set<Team> teamsJoined;

  // Constructor without ID and managed/joined teams
  public User(String firstName, String lastName, String username, String email, String password, LocalDate dob, Set<Role> roles) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.username = username;
    this.email = email;
    this.password = password;
    this.roles = roles;
    this.dob = dob;
  }

  // Custom toString to prevent recursion
  @Override
  public String toString() {
    return "User{" +
           "id=" + id +
           ", username='" + username + '\'' +
           ", email='" + email + '\'' +
           '}';
  }
}
