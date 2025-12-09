package com.example.disasterrelief.repository;

import com.example.disasterrelief.model.Donation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonationRepository extends MongoRepository<Donation, String> {
    // Intentionally minimal — we only use standard MongoRepository methods:
    // findAll(), findById(...), save(...), existsById(...), deleteById(...)
    // Add custom query methods here only if the corresponding fields exist on Donation.
}