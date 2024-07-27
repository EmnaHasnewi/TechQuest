package com.example.ManagementApp.controller;
import com.example.ManagementApp.dto.LoginRequest;
import com.example.ManagementApp.dto.LoginResponse;
import com.example.ManagementApp.entity.User;
import com.example.ManagementApp.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest, HttpSession session) {
        User user = userRepository.findByUsername(loginRequest.getUsername());

        if (user != null && user.getPassword().equals(loginRequest.getPassword())) {
            session.setAttribute("userId", user.getId());
            session.setAttribute("role", user.getRole());

            System.out.println("User logged in. UserId: " + user.getId());
            System.out.println("ROLE: " + user.getRole());

            return ResponseEntity.ok(new LoginResponse(true, "Login successful!", user.getId(), user.getRole()));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponse(false, "Invalid username or password", null, null));
        }
    }
    @GetMapping("/role")
    public ResponseEntity<?> getUserRole(HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not logged in");
        }

        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        // Assuming 'role' is a field in User entity
        String role = user.getRole();
        return ResponseEntity.ok(role);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate(); // Invalide la session actuelle
        return ResponseEntity.ok("Logged out successfully");
    }

    /*@PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest, HttpSession session) {
        User user = userRepository.findByUsername(loginRequest.getUsername());
        if (user != null && user.getPassword().equals(loginRequest.getPassword())) {
            session.setAttribute("userId", user.getId());
            System.out.println("User logged in. UserId: " + user.getId());

            return new LoginResponse(true, "Login successful!");
        } else {
            return new LoginResponse(false, "Invalid username or password");
        }
    }*/
    // Endpoint for creating a user (for testing purposes)
    @PostMapping("/register")
    public String register(@RequestBody User user) {
        userRepository.save(user);
        return "User registered successfully";
    }
}
