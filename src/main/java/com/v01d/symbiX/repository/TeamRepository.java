package com.v01d.symbiX.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.v01d.symbiX.model.Team;

/**
 * TeamRepository
 */
@Repository
public interface TeamRepository extends MongoRepository<Team,String>{

  
}
