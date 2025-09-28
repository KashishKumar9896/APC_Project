package com.example.disasterrelief.controller;

import com.example.disasterrelief.model.Volunteer;
import com.example.disasterrelief.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/volunteers")
public class VolunteerController {

    @Autowired
    private VolunteerService volunteerService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<Volunteer> getAllVolunteers() {
        return volunteerService.getAllVolunteers();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('VOLUNTEER') or hasRole('ADMIN')")
    public ResponseEntity<Volunteer> getVolunteerById(@PathVariable String id) {
        Optional<Volunteer> volunteer = volunteerService.getVolunteerById(id);
        return volunteer.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerVolunteer(@Valid @RequestBody Volunteer volunteer) {
        if (volunteerService.existsByEmail(volunteer.getEmail())) {
            return ResponseEntity.badRequest().body("Error: Email is already in use!");
        }
        
        // Ensure skills is not null
        if (volunteer.getSkills() == null) {
            volunteer.setSkills(new java.util.HashSet<>());
        }
        
        // Encode the password before saving
        volunteer.setPassword(passwordEncoder.encode(volunteer.getPassword()));
        
        Volunteer createdVolunteer = volunteerService.createVolunteer(volunteer);
        return ResponseEntity.ok(createdVolunteer);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('VOLUNTEER') or hasRole('ADMIN')")
    public ResponseEntity<Volunteer> updateVolunteer(@PathVariable String id, @Valid @RequestBody Volunteer volunteerDetails) {
        Volunteer updatedVolunteer = volunteerService.updateVolunteer(id, volunteerDetails);
        if (updatedVolunteer != null) {
            return ResponseEntity.ok(updatedVolunteer);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteVolunteer(@PathVariable String id) {
        boolean deleted = volunteerService.deleteVolunteer(id);
        if (deleted) {
            return ResponseEntity.ok("Volunteer deleted successfully");
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/available")
    @PreAuthorize("hasRole('VOLUNTEER') or hasRole('ADMIN')")
    public List<Volunteer> getAvailableVolunteers() {
        return volunteerService.getAvailableVolunteers();
    }

    @GetMapping("/city/{city}")
    @PreAuthorize("hasRole('VOLUNTEER') or hasRole('ADMIN')")
    public List<Volunteer> getVolunteersByCity(@PathVariable String city) {
        return volunteerService.getVolunteersByCity(city);
    }

    @GetMapping("/state/{state}")
    @PreAuthorize("hasRole('VOLUNTEER') or hasRole('ADMIN')")
    public List<Volunteer> getVolunteersByState(@PathVariable String state) {
        return volunteerService.getVolunteersByState(state);
    }

    @GetMapping("/skill/{skill}")
    @PreAuthorize("hasRole('VOLUNTEER') or hasRole('ADMIN')")
    public List<Volunteer> getVolunteersBySkill(@PathVariable String skill) {
        return volunteerService.getVolunteersBySkill(skill);
    }

    

    
}
