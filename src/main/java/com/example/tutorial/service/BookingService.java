package com.example.tutorial.service;

import com.example.tutorial.entity.Booking;
import com.example.tutorial.entity.Slot;
import com.example.tutorial.entity.TurfDetails;
import com.example.tutorial.entity.User;
import com.example.tutorial.repository.BookingRepository;
import com.example.tutorial.repository.SlotRepositority;
import com.example.tutorial.repository.UserRepositority;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private SlotRepositority slotRepository;

    @Autowired
    private UserRepositority userRepository;


    @Async
    public CompletableFuture<User> findUserAsync(Long userId) {
        return CompletableFuture.supplyAsync(() ->
                userRepository.findById(userId)
                        .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId))
        );
    }

    @Async
    public CompletableFuture<Slot> findSlotAsync(Long slotId) {
        return CompletableFuture.supplyAsync(() ->
                slotRepository.findById(slotId)
                        .orElseThrow(() -> new RuntimeException("Slot not found with ID: " + slotId))
        );
    }

    @Async
    @Transactional
    public void createBooking(Long userId, Long slotId) {
        // Validate the User
        CompletableFuture<User> userFuture=findUserAsync(userId);
        //validate slot
        CompletableFuture<Slot> slotFuture=findSlotAsync(slotId);

        BookingData combinedData=userFuture.thenCombine(slotFuture,(user,slot)->new BookingData(user,slot)).join();

        TurfDetails turfDetails = combinedData.slot.getTurf();

        // Ensure the Slot is not already booked
        if (combinedData.slot.getBooking() != null) {
            throw new RuntimeException("Slot is already booked.");
        }

        // Create a new Booking
        Booking booking = new Booking();
        booking.setUser(combinedData.user);
        booking.setSlot(combinedData.slot);
        booking.setTurfDetails(turfDetails);
        // Link the Slot to the Booking
        combinedData.slot.setBooking(booking);

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

    private static class BookingData{
        User user;
        Slot slot;
        BookingData(User user,Slot slot){
            this.slot=slot;
            this.user=user;
        }
    }
}

