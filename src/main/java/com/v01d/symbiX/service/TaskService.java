package com.v01d.symbiX.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.v01d.symbiX.model.Task;
import com.v01d.symbiX.model.Task;
import com.v01d.symbiX.model.User;
import com.v01d.symbiX.repository.TaskRepository;
import com.v01d.symbiX.repository.TaskRepository;
import com.v01d.symbiX.repository.UserRepository;

/**
 * TaskService
 */
@Service
public class TaskService {

  @Autowired
  private TaskRepository taskRepository;

  @Autowired
  private UserRepository userRepository;

  // Create new Task
  public Task createTask(Task task) {

    return taskRepository.save(task);
  }

  // Edit a task
  public Task editTask(String taskId, Task task) throws Exception {
    Optional<Task> existingTask = taskRepository.findById(taskId);

    if (existingTask.isPresent()) {
      task.setId(taskId);
      return task;
    } else {
      throw new Exception("The Task cannot be found of id " + taskId);
    }
  }

  // Get all the tasks
  public List<Task> getAllTasks() {
    return taskRepository.findAll();
  }

  // Get the task by ID
  public Task getTaskById(String id) throws Exception {
    Optional<Task> existingTask = taskRepository.findById(id);

    if (existingTask.isPresent()) {
      return existingTask.get();
    } else {
      throw new Exception("Cannot find the task of id " + id);
    }
  }

  // Get the task members
  public List<User> getTaskMembers(String taskId) throws Exception {
    Optional<Task> existingTask = taskRepository.findById(taskId);

    if (existingTask.isPresent()) {
      Task task = existingTask.get();

      Set<String> membersIds = task.getCollaboratorsIds();
      System.out.println("These are the members Ids " + membersIds);

      List<User> members = userRepository.findAllById(membersIds);

      System.out.println("And these are the members Objects " + members);

      return members;
    } else {
      throw new Exception("Cannot find the member of the task : " + taskId);
    }
  }
  
  public Task assignMembers(List<String> membersIds, String taskId) throws Exception {

    Optional<Task> existingTask = taskRepository.findById(taskId);

    if (existingTask.isPresent()) {
      Task task = existingTask.get();

      Set<String> originalMembersIds = task.getCollaboratorsIds();
      System.out.println("These are the members Ids " + membersIds);

      originalMembersIds.addAll(membersIds);

      task.setCollaboratorsIds(originalMembersIds);

      // Set the projectList of the users

      List<User> newMembers = userRepository.findAllById(originalMembersIds);

      for (User member : newMembers) {

        List<String> memberTasks = member.getTasksIds();

        if(memberTasks == null){
          memberTasks = new ArrayList<>();
        }

        memberTasks.add(task.getId());

        member.setTasksIds(memberTasks);

        userRepository.save(member);
        taskRepository.save(task);
      }

      return task;
    } else {
      throw new Exception("Cannot find the member of the project : " + taskId);
    }
  }

  // Delete the task By ID
  public String deleteTask(String taskId) {
    try {
      taskRepository.deleteById(taskId);
      return "The task was succesfully deleted";
    } catch (Exception e) {
      return "The task was not deleted, error : " + e.getMessage();
    }
  }

}