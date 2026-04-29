package com.example.disasterrelief.controller;

import com.example.disasterrelief.model.Donation;
import com.example.disasterrelief.service.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/donations")
public class DonationController {

    @Autowired
    private DonationService donationService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<Donation> getAllDonations() {
        return donationService.getAllDonations();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('VOLUNTEER') or hasRole('ADMIN')")
    public ResponseEntity<Donation> getDonationById(@PathVariable String id) {
        Optional<Donation> donation = donationService.getDonationById(id);
        return donation.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<Donation> createDonation(@Valid @RequestBody Donation donation) {
        Donation createdDonation = donationService.createDonation(donation);
        return ResponseEntity.ok(createdDonation);
    }

    @GetMapping("/donor/{email}")
    @PreAuthorize("hasRole('VOLUNTEER') or hasRole('ADMIN')")
    public ResponseEntity<List<Donation>> getDonationsByDonor(@PathVariable String email) {
        List<Donation> donations = donationService.getDonationsByEmail(email);
        return ResponseEntity.ok(donations);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('VOLUNTEER') or hasRole('ADMIN')")
    public ResponseEntity<Donation> updateDonation(@PathVariable String id, @Valid @RequestBody Donation donationDetails) {
        Donation updatedDonation = donationService.updateDonation(id, donationDetails);
        if (updatedDonation != null) {
            return ResponseEntity.ok(updatedDonation);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteDonation(@PathVariable String id) {
        boolean deleted = donationService.deleteDonation(id);
        if (deleted) {
            return ResponseEntity.ok("Donation deleted successfully");
        }
        return ResponseEntity.notFound().build();
    }
}
