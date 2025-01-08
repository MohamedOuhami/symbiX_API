package com.v01d.symbiX.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.v01d.symbiX.model.User;


/**
 * UserRepository
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long>{

  
  Optional<User> findByEmail(String email);
  Optional<User> findByUsername(String username);
}
