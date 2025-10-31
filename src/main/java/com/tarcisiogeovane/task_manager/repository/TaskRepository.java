package com.tarcisiogeovane.task_manager.repository;

import com.tarcisiogeovane.task_manager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Task Repository Interface.
 * * This interface extends JpaRepository, which provides Spring Data JPA with
 * all the standard CRUD (Create, Read, Update, Delete) methods for the Task entity.
 * * We specify <Task, Long> which means:
 * - Task: This repository is for the Task entity.
 * - Long: The type of the Primary Key (our 'id' field) is Long.
 * * Spring Boot will automatically create a bean for this interface.
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    /**
     * Custom query to find all tasks associated with a specific user ID.
     * Spring Data JPA creates the query automatically from the method name.
     */
    List<Task> findByUserId(Long userId);
}