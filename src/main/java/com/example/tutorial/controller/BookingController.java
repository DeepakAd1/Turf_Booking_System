package com.example.tutorial.controller;

import com.example.tutorial.entity.Booking;
import com.example.tutorial.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    // Endpoint to create a new booking
    @PostMapping
    public ResponseEntity<String> createBooking(@RequestParam Long userId, @RequestParam Long slotId) {
        try {
            bookingService.createBooking(userId, slotId);
            return ResponseEntity.ok("Booking created successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Endpoint to get all bookings
    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {
        List<Booking> bookings = bookingService.getAllBookings();
        return ResponseEntity.ok(bookings);
    }

    // Endpoint to check if a slot is available
    @GetMapping("/slots/{slotId}/status")
    public ResponseEntity<String> checkSlotStatus(@PathVariable Long slotId) {
        boolean isFree = bookingService.isSlotFree(slotId);
        if (isFree) {
            return ResponseEntity.ok("The slot is available for booking.");
        } else {
            return ResponseEntity.ok("The slot is already booked.");
        }
    }
}
