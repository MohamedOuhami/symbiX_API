package com.v01d.symbiX.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.v01d.symbiX.config.CustomUserDetails;
import com.v01d.symbiX.model.User;
import com.v01d.symbiX.repository.UserRepository;

/**
 * UserDetailsServiceImpl
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByEmail(username)
      .orElseThrow(() -> new UsernameNotFoundException(String.format("This user was not found , email %s" ,username)));

    return new CustomUserDetails(user);
  }

}
