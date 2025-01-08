package com.v01d.symbiX;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.v01d.symbiX.model.Role;
import com.v01d.symbiX.repository.RoleRepository;

@SpringBootApplication
public class SymbiXApplication {

  public static void main(String[] args) {
    SpringApplication.run(SymbiXApplication.class, args);
  }

  @Autowired
  private RoleRepository roleRepository;

  @Bean
  public CommandLineRunner initializeRoles() {
    return args -> {
      // Check if roles already exist to avoid duplicates
      Role userRole = new Role("ROLE_USER");
      Role adminRole = new Role("ROLE_ADMIN");

      roleRepository.saveAll(List.of(userRole, adminRole));

      System.out.println("Roles initialized successfully!");
    };
  };
}
