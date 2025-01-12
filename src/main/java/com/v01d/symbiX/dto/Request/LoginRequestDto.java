package com.v01d.symbiX.dto.Request;


import lombok.Data;

/**
 * LoginRequestDto
 */
@Data
public class LoginRequestDto {

  private String email;
  private String password;

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
}
