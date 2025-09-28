package com.example.disasterrelief.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Document(collection = "relief_camps")
public class ReliefCamp {
    @Id
    private String id;

    @NotBlank
    @Field("camp_name")
    private String campName;

    @NotBlank
    @Field("location")
    private String location;

    @NotBlank
    @Field("address")
    private String address;

    @NotBlank
    @Field("city")
    private String city;

    @NotBlank
    @Field("state")
    private String state;

    @NotBlank
    @Field("zip_code")
    private String zipCode;

    @NotNull
    @Positive
    @Field("capacity")
    private Integer capacity;

    @Field("current_occupancy")
    private Integer currentOccupancy = 0;

    @Field("camp_manager")
    private String campManager;

    @Field("manager_phone")
    private String managerPhone;

    @Field("manager_email")
    private String managerEmail;

    @Field("status")
    private String status = "ACTIVE"; // ACTIVE, INACTIVE, FULL, CLOSED

    @Field("facilities")
    private Set<String> facilities = new HashSet<>();

    @Field("volunteer_ids")
    private Set<String> volunteerIds = new HashSet<>();

    @Field("donation_ids")
    private Set<String> donationIds = new HashSet<>();

    @Field("created_at")
    private LocalDateTime createdAt;

    @Field("updated_at")
    private LocalDateTime updatedAt;

    @Field("description")
    private String description;

    // Constructors
    public ReliefCamp() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public ReliefCamp(String campName, String location, String address, String city, 
                     String state, String zipCode, Integer capacity, String campManager) {
        this();
        this.campName = campName;
        this.location = location;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.capacity = capacity;
        this.campManager = campManager;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCampName() {
        return campName;
    }

    public void setCampName(String campName) {
        this.campName = campName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getCurrentOccupancy() {
        return currentOccupancy;
    }

    public void setCurrentOccupancy(Integer currentOccupancy) {
        this.currentOccupancy = currentOccupancy;
    }

    public String getCampManager() {
        return campManager;
    }

    public void setCampManager(String campManager) {
        this.campManager = campManager;
    }

    public String getManagerPhone() {
        return managerPhone;
    }

    public void setManagerPhone(String managerPhone) {
        this.managerPhone = managerPhone;
    }

    public String getManagerEmail() {
        return managerEmail;
    }

    public void setManagerEmail(String managerEmail) {
        this.managerEmail = managerEmail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<String> getFacilities() {
        return facilities;
    }

    public void setFacilities(Set<String> facilities) {
        this.facilities = facilities;
    }

    public Set<String> getVolunteerIds() {
        return volunteerIds;
    }

    public void setVolunteerIds(Set<String> volunteerIds) {
        this.volunteerIds = volunteerIds;
    }

    public Set<String> getDonationIds() {
        return donationIds;
    }

    public void setDonationIds(Set<String> donationIds) {
        this.donationIds = donationIds;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isFull() {
        return currentOccupancy >= capacity;
    }

    public int getAvailableCapacity() {
        return capacity - currentOccupancy;
    }
}
