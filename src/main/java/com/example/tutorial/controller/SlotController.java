package com.example.tutorial.controller;

import com.example.tutorial.entity.Slot;
import com.example.tutorial.service.SlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/slots")
public class SlotController {

    @Autowired
    private SlotService slotService;

    // Create or Update Slot
    @PostMapping
    public ResponseEntity<Slot> createOrUpdateSlot(@RequestBody Slot slot) {
        Slot savedSlot = slotService.createOrUpdateSlot(slot);
        return ResponseEntity.ok(savedSlot);
    }

    // Get All Slots
    @GetMapping
    public ResponseEntity<List<Slot>> getAllSlots() {
        List<Slot> slots = slotService.getAllSlots();
        return ResponseEntity.ok(slots);
    }

    // Get Slot by ID
    @GetMapping("/{id}")
    public ResponseEntity<Slot> getSlotById(@PathVariable Long id) {
        Slot slot = slotService.getSlotById(id);
        return ResponseEntity.ok(slot);
    }

    // Delete Slot by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSlot(@PathVariable Long id) {
        slotService.deleteSlot(id);
        return ResponseEntity.ok("Slot deleted successfully");
    }
}
