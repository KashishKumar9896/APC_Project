package com.example.disasterrelief.controller;

import com.example.disasterrelief.model.ReliefCamp;
import com.example.disasterrelief.service.ReliefCampService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/relief-camps")
public class ReliefCampController {

    @Autowired
    private ReliefCampService reliefCampService;

    @GetMapping
    public List<ReliefCamp> getAllReliefCamps() {
        return reliefCampService.getAllReliefCamps();
    }

    @GetMapping("/public")
    public List<ReliefCamp> getPublicReliefCamps() {
        return reliefCampService.getReliefCampsByStatus("ACTIVE");
    }
    @GetMapping("/all")
    public List<ReliefCamp> getAllReliefCam() {
        return reliefCampService.getAllReliefCamps();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('VOLUNTEER') or hasRole('ADMIN')")
    public ResponseEntity<ReliefCamp> getReliefCampById(@PathVariable String id) {
        Optional<ReliefCamp> reliefCamp = reliefCampService.getReliefCampById(id);
        return reliefCamp.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ReliefCamp> createReliefCamp(@Valid @RequestBody ReliefCamp reliefCamp) {
        ReliefCamp createdReliefCamp = reliefCampService.createReliefCamp(reliefCamp);
        return ResponseEntity.ok(createdReliefCamp);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ReliefCamp> updateReliefCamp(@PathVariable String id, @Valid @RequestBody ReliefCamp reliefCampDetails) {
        ReliefCamp updatedReliefCamp = reliefCampService.updateReliefCamp(id, reliefCampDetails);
        if (updatedReliefCamp != null) {
            return ResponseEntity.ok(updatedReliefCamp);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteReliefCamp(@PathVariable String id) {
        boolean deleted = reliefCampService.deleteReliefCamp(id);
        if (deleted) {
            return ResponseEntity.ok("Relief camp deleted successfully");
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/status/{status}")
    @PreAuthorize("hasRole('VOLUNTEER') or hasRole('ADMIN')")
    public List<ReliefCamp> getReliefCampsByStatus(@PathVariable String status) {
        return reliefCampService.getReliefCampsByStatus(status);
    }

    @GetMapping("/city/{city}")
    @PreAuthorize("hasRole('VOLUNTEER') or hasRole('ADMIN')")
    public List<ReliefCamp> getReliefCampsByCity(@PathVariable String city) {
        return reliefCampService.getReliefCampsByCity(city);
    }

    @GetMapping("/state/{state}")
    @PreAuthorize("hasRole('VOLUNTEER') or hasRole('ADMIN')")
    public List<ReliefCamp> getReliefCampsByState(@PathVariable String state) {
        return reliefCampService.getReliefCampsByState(state);
    }

    @GetMapping("/available")
    @PreAuthorize("hasRole('VOLUNTEER') or hasRole('ADMIN')")
    public List<ReliefCamp> getallReliefCamps() {
        return reliefCampService.getAllReliefCamps();
    }

    @PostMapping("/{campId}/volunteers/{volunteerId}")
    @PreAuthorize("hasRole('VOLUNTEER') or hasRole('ADMIN')")
    public ResponseEntity<ReliefCamp> addVolunteerToCamp(@PathVariable String campId, @PathVariable String volunteerId) {
        ReliefCamp reliefCamp = reliefCampService.addVolunteerToCamp(campId, volunteerId);
        if (reliefCamp != null) {
            return ResponseEntity.ok(reliefCamp);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{campId}/volunteers/{volunteerId}")
    @PreAuthorize("hasRole('VOLUNTEER') or hasRole('ADMIN')")
    public ResponseEntity<ReliefCamp> removeVolunteerFromCamp(@PathVariable String campId, @PathVariable String volunteerId) {
        ReliefCamp reliefCamp = reliefCampService.removeVolunteerFromCamp(campId, volunteerId);
        if (reliefCamp != null) {
            return ResponseEntity.ok(reliefCamp);
        }
        return ResponseEntity.badRequest().build();
    }

    
}
