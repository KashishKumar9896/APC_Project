package com.example.disasterrelief.service;

import com.example.disasterrelief.model.ReliefCamp;
import com.example.disasterrelief.repository.ReliefCampRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Service
public class ReliefCampService {

    @Autowired
    private ReliefCampRepository reliefCampRepository;

    public ReliefCampService(ReliefCampRepository reliefCampRepository) {
        this.reliefCampRepository = reliefCampRepository;
    }
    
    public List<ReliefCamp> getAllReliefCamps() {
        return reliefCampRepository.findAll();
    }
    public Optional<ReliefCamp> getReliefCampById(String id) {
        return reliefCampRepository.findById(id);
    }

    public ReliefCamp createReliefCamp(ReliefCamp reliefCamp) {
        reliefCamp.setCreatedAt(java.time.LocalDateTime.now());
        reliefCamp.setUpdatedAt(java.time.LocalDateTime.now());
        return reliefCampRepository.save(reliefCamp);
    }

    public ReliefCamp updateReliefCamp(String id, ReliefCamp reliefCampDetails) {
        Optional<ReliefCamp> optionalReliefCamp = reliefCampRepository.findById(id);
        if (optionalReliefCamp.isPresent()) {
            ReliefCamp reliefCamp = optionalReliefCamp.get();
            reliefCamp.setCampName(reliefCampDetails.getCampName());
            reliefCamp.setLocation(reliefCampDetails.getLocation());
            reliefCamp.setAddress(reliefCampDetails.getAddress());
            reliefCamp.setCity(reliefCampDetails.getCity());
            reliefCamp.setState(reliefCampDetails.getState());
            reliefCamp.setZipCode(reliefCampDetails.getZipCode());
            reliefCamp.setCapacity(reliefCampDetails.getCapacity());
            reliefCamp.setCampManager(reliefCampDetails.getCampManager());
            reliefCamp.setManagerPhone(reliefCampDetails.getManagerPhone());
            reliefCamp.setManagerEmail(reliefCampDetails.getManagerEmail());
            reliefCamp.setStatus(reliefCampDetails.getStatus());
            reliefCamp.setFacilities(reliefCampDetails.getFacilities());
            reliefCamp.setDescription(reliefCampDetails.getDescription());
            reliefCamp.setUpdatedAt(java.time.LocalDateTime.now());
            return reliefCampRepository.save(reliefCamp);
        }
        return null;
    }

    public boolean deleteReliefCamp(String id) {
        if (reliefCampRepository.existsById(id)) {
            reliefCampRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<ReliefCamp> getReliefCampsByStatus(String status) {
        return reliefCampRepository.findByStatus(status);
    }

    public List<ReliefCamp> getReliefCampsByCity(String city) {
        return reliefCampRepository.findByCity(city);
    }

    public List<ReliefCamp> getReliefCampsByState(String state) {
        return reliefCampRepository.findByState(state);
    }

    public List<ReliefCamp> getAvailableReliefCamps() {
        return reliefCampRepository.findByCurrentOccupancyLessThan(Integer.MAX_VALUE);
    }

    public ReliefCamp addVolunteerToCamp(String campId, String volunteerId) {
        Optional<ReliefCamp> optionalReliefCamp = reliefCampRepository.findById(campId);
        if (optionalReliefCamp.isPresent()) {
            ReliefCamp reliefCamp = optionalReliefCamp.get();
            if (!reliefCamp.isFull()) {
                reliefCamp.getVolunteerIds().add(volunteerId);
                reliefCamp.setCurrentOccupancy(reliefCamp.getCurrentOccupancy() + 1);
                reliefCamp.setUpdatedAt(java.time.LocalDateTime.now());
                return reliefCampRepository.save(reliefCamp);
            }
        }
        return null;
    }

    public ReliefCamp removeVolunteerFromCamp(String campId, String volunteerId) {
        Optional<ReliefCamp> optionalReliefCamp = reliefCampRepository.findById(campId);
        if (optionalReliefCamp.isPresent()) {
            ReliefCamp reliefCamp = optionalReliefCamp.get();
            if (reliefCamp.getVolunteerIds().remove(volunteerId)) {
                reliefCamp.setCurrentOccupancy(Math.max(0, reliefCamp.getCurrentOccupancy() - 1));
                reliefCamp.setUpdatedAt(java.time.LocalDateTime.now());
                return reliefCampRepository.save(reliefCamp);
            }
        }
        return null;
    }

    public ReliefCamp addDonationToCamp(String campId, String donationId) {
        Optional<ReliefCamp> optionalReliefCamp = reliefCampRepository.findById(campId);
        if (optionalReliefCamp.isPresent()) {
            ReliefCamp reliefCamp = optionalReliefCamp.get();
            reliefCamp.getDonationIds().add(donationId);
            reliefCamp.setUpdatedAt(java.time.LocalDateTime.now());
            return reliefCampRepository.save(reliefCamp);
        }
        return null;
    }

    public ReliefCamp removeDonationFromCamp(String campId, String donationId) {
        Optional<ReliefCamp> optionalReliefCamp = reliefCampRepository.findById(campId);
        if (optionalReliefCamp.isPresent()) {
            ReliefCamp reliefCamp = optionalReliefCamp.get();
            reliefCamp.getDonationIds().remove(donationId);
            reliefCamp.setUpdatedAt(java.time.LocalDateTime.now());
            return reliefCampRepository.save(reliefCamp);
        }
        return null;
    }
}
