package com.v01d.symbiX.dto;

/**
 * LoginResponse
 */
public class LoginResponse {

  private String email;

  private String token;

  public String getEmail() {
    return email;
  }
  public String getToken() {
    return token;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public LoginResponse(String email,String token){
    this.email = email;
    this.token = token;
  }
}
