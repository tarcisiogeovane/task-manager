package com.tarcisiogeovane.task_manager.service;

import com.tarcisiogeovane.task_manager.model.Task;
import com.tarcisiogeovane.task_manager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service layer for managing Tasks.
 * This class contains the business logic.
 */
@Service
public class TaskService {

    // Dependency Injection: Spring automatically provides an instance of TaskRepository
    @Autowired
    private TaskRepository taskRepository;

    /**
     * Retrieves all tasks from the database.
     * @return a List of all tasks.
     */
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    /**
     * Retrieves a single task by its ID.
     * @param id The ID of the task to retrieve.
     * @return An Optional containing the task if found, or empty if not.
     */
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    /**
     * Creates and saves a new task.
     * @param task The task object to be saved.
     * @return The saved task (with its new ID).
     */
    public Task createTask(Task task) {
        // We could add validation logic here (e.g., check if title is not empty)
        return taskRepository.save(task);
    }

    /**
     * Updates an existing task.
     * @param id The ID of the task to update.
     * @param taskDetails The new details for the task.
     * @return The updated task.
     * @throws RuntimeException if the task is not found.
     */
    public Task updateTask(Long id, Task taskDetails) {
        // Find the existing task by its ID
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));

        // Update the fields of the existing task
        existingTask.setTitle(taskDetails.getTitle());
        existingTask.setDescription(taskDetails.getDescription());
        existingTask.setPriority(taskDetails.getPriority());
        existingTask.setDueDate(taskDetails.getDueDate());
        existingTask.setCompleted(taskDetails.isCompleted());

        // Save the updated task back to the database
        return taskRepository.save(existingTask);
    }

    /**
     * Deletes a task by its ID.
     * @param id The ID of the task to delete.
     * @throws RuntimeException if the task is not found.
     */
    public void deleteTask(Long id) {
        // Check if the task exists before deleting
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));

        taskRepository.delete(task);
    }
}