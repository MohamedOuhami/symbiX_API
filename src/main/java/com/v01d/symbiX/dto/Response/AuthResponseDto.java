package com.v01d.symbiX.dto.Response;


import lombok.Data;

/**
 * AuthResponseDto
 */
@Data
public class AuthResponseDto {

  private String email;
  private String accessToken;
}
