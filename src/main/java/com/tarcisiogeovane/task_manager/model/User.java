package com.tarcisiogeovane.task_manager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

/**
 * Represents a User entity.
 * This class is mapped to the "users" table.
 */

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false) // It ensures username is unique and not null
    private String username;

    @Column(nullable = false)
    private String password;

    /**
     * This defines the "one-to-many" relationship.
     * One User can have many Tasks.
     *
     * - mappedBy = "user": Tells JPA to look for a field named "user" in the Task class
     * to manage the relationship.
     *
     * - cascade = CascadeType.ALL: If a User is deleted, all their associated Tasks
     * will also be deleted.
     *
     * - orphanRemoval = true: If a Task is removed from this list, it should be
     * deleted from the database.
     *
     * - @JsonIgnore: This is VERY important. It prevents an infinite loop when
     * Spring tries to convert this object to JSON (User -> Task list -> Task -> User -> ...).
     */

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Task> tasks;
}