package com.v01d.symbiX.dto;

import jakarta.validation.constraints.*;

/**
 * SignUpRequest
 */
// SignUp dto with necessary info
public record UserDto(

    @NotBlank(message = "The first name cannot be blank") String firstName,

    @NotBlank(message = "The last name cannot be blank") String lastName,

    @NotBlank(message = "The email cannot be blank") @Email(message = "Invalid email format") String email,

    @NotBlank(message = "The password cannot be empty") @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters") String password) {
}
