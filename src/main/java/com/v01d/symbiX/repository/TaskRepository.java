package com.v01d.symbiX.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.v01d.symbiX.model.Task;

/**
 * TaskRepository
 */
@Repository
public interface TaskRepository extends JpaRepository<Task,Long>{

  
}
