package com.example.disasterrelief.repository;

import com.example.disasterrelief.model.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AdminRepository extends MongoRepository<Admin, String> {
    Admin findByEmail(String email);
}
