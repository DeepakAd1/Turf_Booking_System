package com.example.tutorial.entity;

import jakarta.persistence.*;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "booking_seq")
    @SequenceGenerator(name = "booking_seq", sequenceName = "BOOKING_SEQ", allocationSize = 1)
    private long bookingId;

    @ManyToOne
    @JoinColumn(name="user_id",nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "turf_id",nullable = false)
    private TurfDetails turfDetails;

    @OneToOne
    @JoinColumn(name="slot_id",nullable = false)
    private Slot slot;

    public Booking(long bookingId, User user, TurfDetails turfDetails, Slot slot) {
        this.bookingId = bookingId;
        this.user = user;
        this.turfDetails = turfDetails;
        this.slot = slot;
    }

    public Booking() {
    }

    public long getBookingId() {
        return bookingId;
    }

    public void setBookingId(long bookingId) {
        this.bookingId = bookingId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TurfDetails getTurfDetails() {
        return turfDetails;
    }

    public void setTurfDetails(TurfDetails turfDetails) {
        this.turfDetails = turfDetails;
    }

    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }
}
