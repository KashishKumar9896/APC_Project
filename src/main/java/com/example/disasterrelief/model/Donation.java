package com.example.disasterrelief.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Document(collection = "donations")
public class Donation {
    @Id
    private String id;

    @NotBlank
    private String name;

    @NotBlank
    private String email;

    @NotNull
    @Positive
    private Double amount;

    public Donation() {}

    public Donation(String name, String email, Double amount) {
        this.name = name;
        this.email = email;
        this.amount = amount;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
}