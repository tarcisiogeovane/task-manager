package com.tarcisiogeovane.task_manager.service;

import com.tarcisiogeovane.task_manager.model.Task;
import com.tarcisiogeovane.task_manager.model.User; // Import User
import com.tarcisiogeovane.task_manager.repository.TaskRepository;
import com.tarcisiogeovane.task_manager.repository.UserRepository;
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

    // We also need the UserRepository here to find the user
    @Autowired
    private UserRepository userRepository;

    /**
     * Retrieves all tasks FOR A SPECIFIC USER.
     * @param userId The ID of the user.
     * @return A list of tasks for that user.
     */
    public List<Task> getAllTasksByUserId(Long userId) {
        // We will improve this query later
        return taskRepository.findByUserId(userId);
    }

    /**
     * Retrieves a single task by its ID, ONLY IF it belongs to the user.
     * (We will enforce this logic more strictly in Phase 3)
     */
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    /**
     * Creates a new task AND associates it with a user.
     * @param userId The ID of the user creating the task.
     * @param task The task object to be saved.
     * @return The saved task.
     */
    public Task createTask(Long userId, Task task) {
        // Find the user by ID
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        // Set the user on the task
        task.setUser(user);

        // Save the task
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