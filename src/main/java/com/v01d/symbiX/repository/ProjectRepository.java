package com.v01d.symbiX.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.v01d.symbiX.model.Project;

/**
 * ProjectRepository
 */
@Repository
public interface ProjectRepository extends MongoRepository<Project,String> {

  
}
