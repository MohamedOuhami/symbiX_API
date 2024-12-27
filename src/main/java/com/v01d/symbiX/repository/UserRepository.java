package com.v01d.symbiX.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.v01d.symbiX.model.User;

/**
 * UserRepository
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {

  // Returning User By Email
  Optional<User> findByEmail(String email);
}
