package com.example.disasterrelief.repository;

import com.example.disasterrelief.model.ReliefCamp;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReliefCampRepository extends MongoRepository<ReliefCamp, String> {
    List<ReliefCamp> findByStatus(String status);
    List<ReliefCamp> findByCity(String city);
    List<ReliefCamp> findByState(String state);
    List<ReliefCamp> findByCampManager(String campManager);
    List<ReliefCamp> findByCurrentOccupancyLessThan(Integer capacity);
    Optional<ReliefCamp> findByIdAndStatus(String id, String status);
    List<ReliefCamp> findByVolunteerIdsContaining(String volunteerId);
}
