package com.example.tutorial.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
public class TurfDetails implements Serializable {

    @Id
    @SequenceGenerator(name="turf_seq",sequenceName = "TURF_SEQ",allocationSize = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "turf_seq")
    private long turfId;
    private String turfName;
    private String emailId;
    private String turfLocation;

    @OneToMany(mappedBy = "turf",cascade = CascadeType.ALL,orphanRemoval = true)

    @JsonIgnore
    private List<Slot> slots;

    public TurfDetails(long turfId, String turfName, String emailId, String turfLocation, List<Slot> slots) {
        this.turfId = turfId;
        this.turfName = turfName;
        this.emailId = emailId;
        this.turfLocation = turfLocation;
        this.slots = slots;
    }

    public List<Slot> getSlots() {
        return slots;
    }

    public void setSlots(List<Slot> slots) {
        this.slots = slots;
    }

    public TurfDetails() {
    }

    public long getTurfId() {
        return turfId;
    }

    public void setTurfId(long turfId) {
        this.turfId = turfId;
    }

    public String getTurfName() {
        return turfName;
    }

    public void setTurfName(String turfName) {
        this.turfName = turfName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getTurfLocation() {
        return turfLocation;
    }

    public void setTurfLocation(String turfLocation) {
        this.turfLocation = turfLocation;
    }
}
