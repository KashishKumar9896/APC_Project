package com.example.disasterrelief.repository;

import com.example.disasterrelief.model.Donation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface DonationRepository extends MongoRepository<Donation, String> {
    List<Donation> findByStatus(String status);
    List<Donation> findByDonationType(String donationType);
    List<Donation> findByReliefCampId(String reliefCampId);
    List<Donation> findByDonorEmail(String donorEmail);
    List<Donation> findByCreatedAtBetween(java.time.LocalDateTime start, java.time.LocalDateTime end);
    Optional<Donation> findByIdAndStatus(String id, String status);
}
