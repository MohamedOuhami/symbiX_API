package com.v01d.symbiX.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * User
 */
@Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

  @Id
  private String id;

  private String firstName;

  private String lastName;

  private String email;

  private String profilePic;

  private LocalDate dob;

  private String password;

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

}
