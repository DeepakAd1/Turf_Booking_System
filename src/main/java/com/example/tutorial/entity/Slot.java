package com.example.tutorial.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Slot {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "slot_seq")
    @SequenceGenerator(name="slot_seq",sequenceName = "SLOT_SEQ",allocationSize = 1)
    private Long id;

    private LocalDateTime startTime; // Example: "10:00 AM"
    private LocalDateTime endTime;   // Example: "11:00 AM"

    @ManyToOne
    @JoinColumn(name = "turf_id", nullable = false) // Foreign key for TurfDetails

    private TurfDetails turf;

    @OneToOne(mappedBy = "slot", cascade = CascadeType.ALL)
    private Booking booking;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Slot() {
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Slot(Long id, LocalDateTime startTime, LocalDateTime endTime, TurfDetails turf, Booking booking) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.turf = turf;
        this.booking = booking;
    }

    public TurfDetails getTurf() {
        return turf;
    }

    public void setTurf(TurfDetails turf) {
        this.turf = turf;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
}

