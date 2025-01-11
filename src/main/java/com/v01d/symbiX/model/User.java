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
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String firstName;
  private String lastName;

  // We want that the username be not null, and to be unique
  @Column(nullable = false, unique = true)
  private String username;

  // Same here, we want the email to be not null and to be unique
  @Column(nullable = false, unique = true)
  private String email;

  // We want now the password to not be null, but it's okay if It was not unique
  @Column(nullable = false)
  private String password;

  private LocalDate dob;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
  private Set<Role> roles;

  // Teams Attributes
  @OneToMany(mappedBy = "leader", orphanRemoval = true)
  @JsonIgnore
  private List<Team> teamsManaged;

  @ManyToMany(mappedBy="members")
  @JsonIgnore
  private Set<Team> teamsJoined;

  public User() {
  }

  public User(String firstName, String lastName, String username, String email, String password, LocalDate dob,
      Set<Role> roles) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.username = username;
    this.email = email;
    this.password = password;
    this.roles = roles;
    this.dob = dob;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }

  public LocalDate getDob() {
    return dob;
  }

  public void setDob(LocalDate dob) {
    this.dob = dob;
  }

  public List<Team> getTeamsManaged() {
    return teamsManaged;
  }

  public void setTeamsManaged(List<Team> teamsManaged) {
    this.teamsManaged = teamsManaged;
  }

  public Set<Team> getTeamsJoined() {
    return teamsJoined;
  }

  public void setTeamsJoined(Set<Team> teamsJoined) {
    this.teamsJoined = teamsJoined;
  }
}
