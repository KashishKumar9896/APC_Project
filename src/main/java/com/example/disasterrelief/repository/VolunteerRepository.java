package com.example.disasterrelief.repository;

import com.example.disasterrelief.model.Volunteer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface VolunteerRepository extends MongoRepository<Volunteer, String> {
    Optional<Volunteer> findByEmail(String email);
    List<Volunteer> findByIsAvailable(boolean isAvailable);
    List<Volunteer> findByCity(String city);
    List<Volunteer> findByState(String state);
    List<Volunteer> findBySkillsContaining(String skill);
    boolean existsByEmail(String email);
}
