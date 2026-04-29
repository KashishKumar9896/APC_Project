package com.example.disasterrelief.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FaviconController {

    // Simple SVG favicon served at /favicon.ico to avoid 404s
    private static final String SVG = "<svg xmlns='http://www.w3.org/2000/svg' width='64' height='64' viewBox='0 0 64 64'><rect width='64' height='64' fill='#28a745'/><text x='50%' y='50%' fill='white' font-size='32' font-family='Arial' dominant-baseline='middle' text-anchor='middle'>D</text></svg>";

    @GetMapping(value = "/favicon.ico", produces = "image/svg+xml")
    public ResponseEntity<String> favicon() {
        return ResponseEntity.ok(SVG);
    }
}
