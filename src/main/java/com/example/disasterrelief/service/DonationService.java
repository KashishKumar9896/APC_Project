package com.example.disasterrelief.service;

import com.example.disasterrelief.model.Donation;
import com.example.disasterrelief.repository.DonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DonationService {

    @Autowired
    private DonationRepository donationRepository;

    public List<Donation> getAllDonations() {
        return donationRepository.findAll();
    }

    public Optional<Donation> getDonationById(String id) {
        return donationRepository.findById(id);
    }

    public Donation createDonation(Donation donation) {
        donation.setCreatedAt(java.time.LocalDateTime.now());
        donation.setUpdatedAt(java.time.LocalDateTime.now());
        return donationRepository.save(donation);
    }

    public Donation updateDonation(String id, Donation donationDetails) {
        Optional<Donation> optionalDonation = donationRepository.findById(id);
        if (optionalDonation.isPresent()) {
            Donation donation = optionalDonation.get();
            donation.setDonorName(donationDetails.getDonorName());
            donation.setDonorEmail(donationDetails.getDonorEmail());
            donation.setDonorPhone(donationDetails.getDonorPhone());
            donation.setDonationType(donationDetails.getDonationType());
            donation.setAmount(donationDetails.getAmount());
            donation.setDescription(donationDetails.getDescription());
            donation.setReliefCampId(donationDetails.getReliefCampId());
            donation.setStatus(donationDetails.getStatus());
            donation.setNotes(donationDetails.getNotes());
            donation.setUpdatedAt(java.time.LocalDateTime.now());
            return donationRepository.save(donation);
        }
        return null;
    }

    public boolean deleteDonation(String id) {
        if (donationRepository.existsById(id)) {
            donationRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Donation> getDonationsByStatus(String status) {
        return donationRepository.findByStatus(status);
    }

    public List<Donation> getDonationsByType(String donationType) {
        return donationRepository.findByDonationType(donationType);
    }

    public List<Donation> getDonationsByReliefCamp(String reliefCampId) {
        return donationRepository.findByReliefCampId(reliefCampId);
    }

    public List<Donation> getDonationsByDonor(String donorEmail) {
        return donationRepository.findByDonorEmail(donorEmail);
    }

    public Donation approveDonation(String id, String approvedBy) {
        Optional<Donation> optionalDonation = donationRepository.findById(id);
        if (optionalDonation.isPresent()) {
            Donation donation = optionalDonation.get();
            donation.setStatus("APPROVED");
            donation.setApprovedBy(approvedBy);
            donation.setUpdatedAt(java.time.LocalDateTime.now());
            return donationRepository.save(donation);
        }
        return null;
    }

    public Donation rejectDonation(String id, String notes) {
        Optional<Donation> optionalDonation = donationRepository.findById(id);
        if (optionalDonation.isPresent()) {
            Donation donation = optionalDonation.get();
            donation.setStatus("REJECTED");
            donation.setNotes(notes);
            donation.setUpdatedAt(java.time.LocalDateTime.now());
            return donationRepository.save(donation);
        }
        return null;
    }

    public Donation distributeDonation(String id) {
        Optional<Donation> optionalDonation = donationRepository.findById(id);
        if (optionalDonation.isPresent()) {
            Donation donation = optionalDonation.get();
            donation.setStatus("DISTRIBUTED");
            donation.setUpdatedAt(java.time.LocalDateTime.now());
            return donationRepository.save(donation);
        }
        return null;
    }
}
