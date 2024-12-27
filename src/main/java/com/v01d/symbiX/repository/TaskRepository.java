package com.v01d.symbiX.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.v01d.symbiX.model.Task;

/**
 * TaskRepository
 */
public interface TaskRepository extends MongoRepository<Task,String>{

  
}
