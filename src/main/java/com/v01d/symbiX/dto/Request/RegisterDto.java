package com.v01d.symbiX.dto.Request;

import java.util.Set;

import lombok.Data;

/**
 * RegisterDto
 */
@Data
public class RegisterDto {

  private String firstName;
  private String lastName;
  private String email;
  private String password;
  private String username;
  private Set<String> role;

  public RegisterDto(String firstName, String lastName, String username, String email, String password,
      Set<String> role) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.username = username;
    this.email = email;
    this.password = password;
    this.role = role;
  }

}
