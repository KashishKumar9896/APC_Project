package com.example.disasterrelief.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDateTime;

@Document(collection = "donations")
public class Donation {
    @Id
    private String id;

    @NotBlank
    @Field("donor_name")
    private String donorName;

    @NotBlank
    @Field("donor_email")
    private String donorEmail;

    @NotBlank
    @Field("donor_phone")
    private String donorPhone;

    @NotBlank
    @Field("donation_type")
    private String donationType; // CASH, FOOD, CLOTHING, MEDICAL_SUPPLIES, OTHER

    @NotNull
    @Positive
    @Field("amount")
    private Double amount;

    @Field("description")
    private String description;

    @Field("relief_camp_id")
    private String reliefCampId;

    @Field("status")
    private String status = "PENDING"; // PENDING, APPROVED, REJECTED, DISTRIBUTED

    @Field("created_at")
    private LocalDateTime createdAt;

    @Field("updated_at")
    private LocalDateTime updatedAt;

    @Field("approved_by")
    private String approvedBy;

    @Field("notes")
    private String notes;

    // Constructors
    public Donation() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Donation(String donorName, String donorEmail, String donorPhone, 
                   String donationType, Double amount, String description) {
        this();
        this.donorName = donorName;
        this.donorEmail = donorEmail;
        this.donorPhone = donorPhone;
        this.donationType = donationType;
        this.amount = amount;
        this.description = description;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDonorName() {
        return donorName;
    }

    public void setDonorName(String donorName) {
        this.donorName = donorName;
    }

    public String getDonorEmail() {
        return donorEmail;
    }

    public void setDonorEmail(String donorEmail) {
        this.donorEmail = donorEmail;
    }

    public String getDonorPhone() {
        return donorPhone;
    }

    public void setDonorPhone(String donorPhone) {
        this.donorPhone = donorPhone;
    }

    public String getDonationType() {
        return donationType;
    }

    public void setDonationType(String donationType) {
        this.donationType = donationType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReliefCampId() {
        return reliefCampId;
    }

    public void setReliefCampId(String reliefCampId) {
        this.reliefCampId = reliefCampId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
