package com.example.ManagementApp.controller;

import com.example.ManagementApp.dto.RequestDTO;
import com.example.ManagementApp.entity.Request;
import com.example.ManagementApp.service.RequestService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Clock;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/requests")
public class RequestController {

    @Autowired
    private RequestService requestService;

    /*GetMapping
    public List<Request> getAllRequests() {
        return requestService.getAllRequests();
    }*/
    @GetMapping
    public List<RequestDTO> getAllRequests() {
        return requestService.getAllRequests();
    }

    @PostMapping
    public ResponseEntity<?> createRequest(@RequestBody Request request, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId != null) {
            // Associate the user ID with the request
            request.setUserId(userId);
            // Save the request using RequestService
            requestService.saveRequest(request);

            // Create a JSON response
            Map<String, String> response = new HashMap<>();
            response.put("message", "Request created successfully");

            return ResponseEntity.ok(response);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("error", "User not logged in");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getRequestsByUserId(@PathVariable Long userId) {
        List<Request> requests = requestService.getRequestsByUserId(userId);

        if (requests != null && !requests.isEmpty()) {
            return ResponseEntity.ok(requests);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("error", "No requests found for user ID: " + userId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
    @PutMapping("/{requestId}/approve")
    public ResponseEntity<?> approveRequest(@PathVariable Long requestId) {
        requestService.approveRequest(requestId);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Request approved successfully");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{requestId}/refuse")
    public ResponseEntity<?> refuseRequest(@PathVariable Long requestId) {
        requestService.refuseRequest(requestId);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Request refused successfully");
        return ResponseEntity.ok(response);
    }

}
