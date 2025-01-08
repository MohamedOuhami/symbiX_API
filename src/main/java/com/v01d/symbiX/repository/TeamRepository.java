package com.v01d.symbiX.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.v01d.symbiX.model.Team;

/**
 * TeamRepository
 */
@Repository
public interface TeamRepository extends JpaRepository<Team,Long>{

  
}
