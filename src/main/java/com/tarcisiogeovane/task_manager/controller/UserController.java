package com.tarcisiogeovane.task_manager.controller;

import com.tarcisiogeovane.task_manager.model.User;
import com.tarcisiogeovane.task_manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller for managing Users.
 * For now, just for registration.
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * HTTP POST /api/users
     * Creates (registers) a new user.
     * @param user User data from the request body.
     * @return The created user and HTTP 201.
     */
    @PostMapping("/register") // We'll use /api/users/register
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }
}