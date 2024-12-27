package com.v01d.symbiX.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.v01d.symbiX.model.User;
import com.v01d.symbiX.repository.UserRepository;

/**
 * UserService
 */
@Service
@Transactional(readOnly = true)
public class UserService {


  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  // Signup function
  @Transactional
  public void signup(User user){
    String email = user.getEmail();

    Optional<User> existingUser = userRepository.findByEmail(email);

    // Check if the user already exists
    if(existingUser.isPresent()){
      throw new DuplicateKeyException("This user already exists");
    }

    // Hash the password
    String hashedPassword = passwordEncoder.encode(user.getPassword());
    user.setPassword(hashedPassword);
    userRepository.save(user);
  }
  

}
