package com.v01d.symbiX.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.v01d.symbiX.dto.UserDto;
import com.v01d.symbiX.model.User;
import com.v01d.symbiX.service.UserService;

import jakarta.validation.Valid;

/**
 * AuthController
 */
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

  
  @Autowired
  private UserService userService;
  // Register a new User
  @PostMapping("/signup")
  public ResponseEntity<Void> signup(@Valid @RequestBody User user){
    userService.signup(user);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }
}
