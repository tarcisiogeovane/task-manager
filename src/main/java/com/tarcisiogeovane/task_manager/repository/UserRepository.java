package com.tarcisiogeovane.task_manager.repository;

import com.tarcisiogeovane.task_manager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for the User entity.
 * Provides standard CRUD methods via JpaRepository.
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Custom query method to find a user by their username.
     * Spring Data JPA will automatically implement this method for us.
     * We will use it later (Security).
     */
    Optional<User> findByUsername(String username);
}