package com.v01d.symbiX.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * EntryPointController
 */
@RestController
@RequestMapping("/api/v1/entry")
public class EntryPointController {

  @GetMapping
  public ResponseEntity<String> hello_app(){
    return ResponseEntity.ok("Hello Symbix"); 
  }
  
}
