package com.example.tutorial.service;

import com.example.tutorial.entity.Slot;
import com.example.tutorial.entity.TurfDetails;
import com.example.tutorial.repository.SlotRepositority;
import com.example.tutorial.repository.TurfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SlotService {

    @Autowired
    private SlotRepositority slotRepository;

    @Autowired
    private TurfRepository turfRepository;

    public Slot createOrUpdateSlot(Slot slot) {
        // Check if the TurfDetails entity with the given ID exists in the database


        TurfDetails turf = turfRepository.findById(slot.getTurf().getTurfId())
                .orElseThrow(() -> new RuntimeException("Turf not found with id: " + slot.getTurf().getTurfId()));

        // Set the retrieved TurfDetails to the Slot object
        slot.setTurf(turf);

        // Now save the Slot object
        return slotRepository.save(slot);
    }


    // Get all Slots
    public List<Slot> getAllSlots() {
        return slotRepository.findAll();
    }

    // Get Slot by ID
    public Slot getSlotById(Long id) {
        return slotRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Slot not found with id: " + id));
    }

    // Delete Slot by ID
    public void deleteSlot(Long id) {
        Slot slot = getSlotById(id);
        slotRepository.delete(slot);
    }
}

