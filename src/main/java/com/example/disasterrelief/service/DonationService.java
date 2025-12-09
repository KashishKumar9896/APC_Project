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
        return donationRepository.save(donation);
    }

    public Donation updateDonation(String id, Donation donationDetails) {
        Optional<Donation> optionalDonation = donationRepository.findById(id);
        if (optionalDonation.isPresent()) {
            Donation donation = optionalDonation.get();
            donation.setName(donationDetails.getName());
            donation.setEmail(donationDetails.getEmail());
            donation.setAmount(donationDetails.getAmount());
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
}
