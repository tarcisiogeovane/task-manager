package com.tarcisiogeovane.task_manager.controller;

import com.tarcisiogeovane.task_manager.model.Task;
import com.tarcisiogeovane.task_manager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for managing Tasks.
 * This class exposes endpoints for the client (e.g., a web front-end) to interact with.
 */

@RestController // Marks this class as a Controller that handles REST requests
@RequestMapping("/api") // All endpoints in this class will start with /api
public class TaskController {

    // Inject the service layer
    @Autowired
    private TaskService taskService;

    /**
     * HTTP GET /api/users/{userId}/tasks
     * Gets all tasks for a specific user.
     * @param userId The user's ID from the URL path.
     * @return A List of tasks.
     */
    @GetMapping("/users/{userId}/tasks")
    public List<Task> getAllTasksByUserId(@PathVariable Long userId) {
        return taskService.getAllTasksByUserId(userId);
    }

    /**
     * HTTP POST /api/users/{userId}/tasks
     * Creates a new task for a specific user.
     * @param userId The user's ID from the URL path.
     * @param task The Task object from the request body (JSON).
     * @return The created task and HTTP 201.
     */
    @PostMapping("/users/{userId}/tasks")
    public ResponseEntity<Task> createTask(@PathVariable Long userId, @RequestBody Task task) {
        Task createdTask = taskService.createTask(userId, task);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

    /**
     * HTTP PUT /api/tasks/{id}
     * Updates an existing task.
     * @param id The ID of the task to update.
     * @param taskDetails The new task data from the request body (JSON).
     * @return The updated task and HTTP Status 200 (OK).
     */
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task taskDetails) {
        try {
            Task updatedTask = taskService.updateTask(id, taskDetails);
            return ResponseEntity.ok(updatedTask);
        } catch (RuntimeException e) {
            // This catches the exception thrown from the service if the task is not found
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * HTTP DELETE /api/tasks/{id}
     * Deletes a task by its ID.
     * @param id The ID of the task to delete.
     * @return HTTP Status 204 (No Content) if successful.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        try {
            taskService.deleteTask(id);
            return ResponseEntity.noContent().build(); // Return 204 (No Content)
        } catch (RuntimeException e) {
            // This catches the exception thrown from the service if the task is not found
            return ResponseEntity.notFound().build();
        }
    }
}