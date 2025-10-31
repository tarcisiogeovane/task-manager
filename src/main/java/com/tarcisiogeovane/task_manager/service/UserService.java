package com.tarcisiogeovane.task_manager.service;

import com.tarcisiogeovane.task_manager.model.User;
import com.tarcisiogeovane.task_manager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service layer for managing Users.
 * For now, it only handles creating a user.
 */

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Creates a new user.
     * WARNING: In a real application, we MUST hash the password.
     * We will add password hashing (Security).
     * @param user The user object to save.
     * @return The saved user.
     */

    public User createUser(User user) {
        // Later, we will add password encoding here.
        return userRepository.save(user);
    }
}