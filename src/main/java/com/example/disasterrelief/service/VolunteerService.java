package com.example.disasterrelief.service;

import com.example.disasterrelief.model.Volunteer;
import com.example.disasterrelief.repository.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class VolunteerService {

    @Autowired
    private VolunteerRepository volunteerRepository;

    public List<Volunteer> getAllVolunteers() {
        return volunteerRepository.findAll();
    }

    public Optional<Volunteer> getVolunteerById(String id) {
        return volunteerRepository.findById(id);
    }

    public Optional<Volunteer> getVolunteerByEmail(String email) {
        return volunteerRepository.findByEmail(email);
    }

    public Volunteer createVolunteer(Volunteer volunteer) {
        volunteer.setCreatedAt(java.time.LocalDateTime.now());
        volunteer.setUpdatedAt(java.time.LocalDateTime.now());
        return volunteerRepository.save(volunteer);
    }

    public Volunteer updateVolunteer(String id, Volunteer volunteerDetails) {
        Optional<Volunteer> optionalVolunteer = volunteerRepository.findById(id);
        if (optionalVolunteer.isPresent()) {
            Volunteer volunteer = optionalVolunteer.get();
            volunteer.setFirstName(volunteerDetails.getFirstName());
            volunteer.setLastName(volunteerDetails.getLastName());
            volunteer.setEmail(volunteerDetails.getEmail());
            volunteer.setPhoneNumber(volunteerDetails.getPhoneNumber());
            volunteer.setAddress(volunteerDetails.getAddress());
            volunteer.setCity(volunteerDetails.getCity());
            volunteer.setState(volunteerDetails.getState());
            volunteer.setZipCode(volunteerDetails.getZipCode());
            volunteer.setSkills(volunteerDetails.getSkills());
            volunteer.setEmergencyContact(volunteerDetails.getEmergencyContact());
            volunteer.setEmergencyPhone(volunteerDetails.getEmergencyPhone());
            volunteer.setAvailable(volunteerDetails.isAvailable());
            volunteer.setUpdatedAt(java.time.LocalDateTime.now());
            return volunteerRepository.save(volunteer);
        }
        return null;
    }

    public boolean deleteVolunteer(String id) {
        if (volunteerRepository.existsById(id)) {
            volunteerRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Volunteer> getAvailableVolunteers() {
        return volunteerRepository.findByIsAvailable(true);
    }

    public List<Volunteer> getVolunteersByCity(String city) {
        return volunteerRepository.findByCity(city);
    }

    public List<Volunteer> getVolunteersByState(String state) {
        return volunteerRepository.findByState(state);
    }

    public List<Volunteer> getVolunteersBySkill(String skill) {
        return volunteerRepository.findBySkillsContaining(skill);
    }

    public boolean existsByEmail(String email) {
        return volunteerRepository.existsByEmail(email);
    }
}
