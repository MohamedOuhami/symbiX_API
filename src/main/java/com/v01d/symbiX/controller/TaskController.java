package com.v01d.symbiX.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.v01d.symbiX.model.Task;
import com.v01d.symbiX.model.Task;
import com.v01d.symbiX.model.User;
import com.v01d.symbiX.repository.TaskRepository;
import com.v01d.symbiX.service.TaskService;

/**
 * TaskController
 */
@RestController
@RequestMapping("/api/v1/task")
public class TaskController {

  @Autowired
  private TaskService taskService;

  @GetMapping
  public ResponseEntity<List<Task>> getAlltasks() {

    return ResponseEntity.ok(taskService.getAllTasks());
  }

  // Get task by taskId
  @GetMapping("/{taskId}")
  public ResponseEntity<Task> gettaskById(@PathVariable String taskId) throws Exception{
    return ResponseEntity.ok(taskService.getTaskById(taskId));
  }

  // Create a new Task
  @PostMapping
  public ResponseEntity<Task> createtask(@RequestBody Task task) {
    return ResponseEntity.ok(taskService.createTask(task));
  }

  // Edit a task
  @PutMapping("/{taskId}")
  public ResponseEntity<Task> edittask(@RequestBody Task task, @PathVariable String taskId) {
    try {
      Task originalTask = taskService.getTaskById(taskId);
      task.setId(originalTask.getId());

      return ResponseEntity.ok(taskService.createTask(task));
    } catch (Exception e) {
      System.err.println("The task cannot be updated due to error : " + e.getMessage());

      return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
    }
  }

  // Delete a task
  @DeleteMapping("/{taskId}")
  public ResponseEntity<String> deleteTask(@PathVariable String taskId) {
    try {
      taskService.deleteTask(taskId);
      return ResponseEntity.status(HttpStatus.OK).body("Task deleted successfully");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.CONFLICT).body("Task could not be deleted due to " + e.getMessage());
    }
  }

  // Get the task members
  @GetMapping("/members/{taskId}")
  public ResponseEntity<List<User>> getTaskMembers(@PathVariable String taskId) {

    try {
      List<User> taskMembers = taskService.getTaskMembers(taskId);
      return ResponseEntity.status(HttpStatus.OK).body(taskMembers);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
    }
  }

  // Assign new members
  @PutMapping("{taskId}/assign")
  public ResponseEntity<Task> assignTeam(@RequestBody List<String> membersIds,@RequestParam String taskId) throws Exception{
    return ResponseEntity.ok(taskService.assignMembers(membersIds, taskId));
  }

  // Assign new members
  @PutMapping("{taskId}/assignMembers")
  public ResponseEntity<Task> assignMember(@RequestBody List<String> membersIds,@PathVariable String taskId) throws Exception{
    return ResponseEntity.ok(taskService.assignMembers(membersIds, taskId));
  }



}


