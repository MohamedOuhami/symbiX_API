package com.v01d.symbiX.service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.v01d.symbiX.dto.Request.LoginRequestDto;
import com.v01d.symbiX.dto.Request.RegisterDto;
import com.v01d.symbiX.model.Role;
import com.v01d.symbiX.model.User;
import com.v01d.symbiX.repository.RoleRepository;
import com.v01d.symbiX.repository.UserRepository;

/**
 * AuthServiceImpl
 */
@Service
public class AuthServiceImpl {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtTokenProvider jwtTokenProvider;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RoleRepository roleRepository;

  // Method to login to the API
  public String login(LoginRequestDto loginDto) {

    // AuthenticationManager is used to authenticate the user
    Authentication authentication = authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));

    // SecurityContextHolder is used to allows the rest of the application to know
    // that the user is authenticated and can use user data from Authentication
    // object
    SecurityContextHolder.getContext().setAuthentication(authentication);

    // Generate the token based on the email and a secret key
    String token = jwtTokenProvider.generateToken(authentication);

    // Return the token to the user
    return token;
  }

  public User register(RegisterDto registerDto) throws Exception {

    // Get the email and the username coming from the request
    String registerEmail = registerDto.getEmail();
    String registerUsername = registerDto.getUsername();

    Optional<User> userByEmail = userRepository.findByEmail(registerEmail);
    Optional<User> userByUsername = userRepository.findByUsername(registerUsername);
    if (userByEmail.isPresent() || userByUsername.isPresent()) {

      throw new Exception("The user already exists");
    } else {
      Set<Role> addedRoles = roleRepository.findAllByNameIn(registerDto.getRole());
      String enteredPassword = registerDto.getPassword();

      // Hash the password before storing
      String hashedPassword = BCrypt.hashpw(enteredPassword, BCrypt.gensalt());
      User userToSave = new User(registerDto.getFirstName(), registerDto.getLastName(), registerDto.getUsername(),
          registerDto.getEmail(), hashedPassword, LocalDate.of(2001, 06, 27), addedRoles);

      User userSaved = userRepository.save(userToSave);
      return userSaved;
    }

  }

}
