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
@RequestMapping("/api/tasks") // All endpoints in this class will start with /api/tasks
public class TaskController {

    // Inject the service layer
    @Autowired
    private TaskService taskService;

    /**
     * HTTP GET /api/tasks
     * Gets all tasks.
     * @return A List of all tasks and HTTP Status 200 (OK).
     */
    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    /**
     * HTTP GET /api/tasks/{id}
     * Gets a single task by its ID.
     * @param id The ID from the URL path.
     * @return A ResponseEntity containing the Task or HTTP Status 404 (Not Found).
     */
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id)
                .map(task -> ResponseEntity.ok().body(task)) // If found, return 200 (OK)
                .orElse(ResponseEntity.notFound().build()); // If not found, return 404 (Not Found)
    }

    /**
     * HTTP POST /api/tasks
     * Creates a new task.
     * @param task The Task object from the request body (JSON).
     * @return The created task and HTTP Status 201 (Created).
     */
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task createdTask = taskService.createTask(task);
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