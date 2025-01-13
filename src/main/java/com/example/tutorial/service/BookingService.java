package com.example.tutorial.service;

import com.example.tutorial.entity.Booking;
import com.example.tutorial.entity.Slot;
import com.example.tutorial.entity.User;
import com.example.tutorial.repository.BookingRepository;
import com.example.tutorial.repository.SlotRepositority;
import com.example.tutorial.repository.UserRepositority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private SlotRepositority slotRepository;

    @Autowired
    private UserRepositority userRepository;

    public void createBooking(Long userId, Long slotId) {
        // Validate the User
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Validate the Slot
        Slot slot = slotRepository.findById(slotId)
                .orElseThrow(() -> new RuntimeException("Slot not found"));

        // Ensure the Slot is not already booked
        if (slot.getBooking() != null) {
            throw new RuntimeException("Slot is already booked.");
        }

        // Create a new Booking
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setSlot(slot);

        // Link the Slot to the Booking
        slot.setBooking(booking);

        // Save the Booking
        bookingRepository.save(booking);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public boolean isSlotFree(Long slotId) {
        Slot slot = slotRepository.findById(slotId)
                .orElseThrow(() -> new RuntimeException("Slot not found"));

        return slot.getBooking() == null; // Return true if no booking exists
    }
}

