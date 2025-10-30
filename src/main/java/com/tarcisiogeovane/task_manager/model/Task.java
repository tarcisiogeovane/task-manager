package com.tarcisiogeovane.task_manager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.Data;

/**
 * Represents a Task entity in the database.
 * This class is mapped to the "tasks" table.
 * * We use @Data from Lombok to automatically generate:
 * - Getters for all fields
 * - Setters for all fields
 * - equals() method
 * - hashCode() method
 * - toString() method
 */
@Data
@Entity // Tells JPA that this class is an entity (it maps to a database table)
@Table(name = "tasks") // Specifies the name of the table in the database
public class Task {

    @Id // Marks this field as the Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increments the ID (1, 2, 3...)
    private Long id;

    // The title of the task.
    private String title;

    // A detailed description of the task.
    private String description;

    // Priority of the task (e.g., "HIGH", "MEDIUM", "LOW")
    private String priority;

    // The due date for the task.
    private LocalDate dueDate;

    // Indicates whether the task has been completed.
    private boolean completed = false; // Default to false
}