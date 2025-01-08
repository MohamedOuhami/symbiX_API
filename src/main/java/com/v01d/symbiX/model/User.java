package com.v01d.symbiX.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

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

/**
 * User
 */
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

  @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = false)
  private List<Project> projectsLeader;

  @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = false)
  private List<Team> teamsLeader;


  // Do a Many To Many relationship between the users and the role
  @ManyToMany(fetch = FetchType.EAGER)
  // Create a join table that has the id of the user_id referencing the id
  // attribute of the user, and role_if for the attribute id of the role
  @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
  private Set<Role> roles;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "users_teams", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "team_id", referencedColumnName = "id"))
  private List<Team> teams;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "users_projects", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id"))
  private List<Project> projects;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "users_tasks", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "task_id", referencedColumnName = "id"))
  private List<Task> tasks;

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

  public List<Team> getTeams() {
    return teams;
  }

  public void setTeams(List<Team> teams) {
    this.teams = teams;
  }

  public List<Project> getProjects() {
    return projects;
  }

  public void setProjects(List<Project> projects) {
    this.projects = projects;
  }

  public List<Task> getTasks() {
    return tasks;
  }

  public void setTasks(List<Task> tasks) {
    this.tasks = tasks;
  }

}
