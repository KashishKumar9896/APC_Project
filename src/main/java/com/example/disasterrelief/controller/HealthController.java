package com.example.disasterrelief.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HealthController {

    @GetMapping("/health")
    public Map<String, Object> health() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "UP");
        response.put("service", "Disaster Relief Management System");
        response.put("timestamp", java.time.LocalDateTime.now());
        return response;
    }

    @GetMapping("/api")
    public Map<String, String> api() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Disaster Relief Management System API");
        response.put("version", "1.0.0");
        response.put("status", "Running");
        return response;
    }
}
