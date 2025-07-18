package com.example.demo.controller;

import com.example.model.User;
import com.example.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class UserController {

    private final UserService userService;

//    @PostMapping"/{userId}/avatar")
    public ResponseEntity<User> uploadAvatar(
            @PathVariable Long userId,
            @RequestParam MultipartFile avatar) throws IOException {
        return ResponseEntity.ok(userService.updateAvatar(userId, avatar));
    }

//    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }
}
