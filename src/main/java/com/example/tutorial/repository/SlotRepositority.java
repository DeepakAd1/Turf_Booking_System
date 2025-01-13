package com.example.tutorial.repository;

import com.example.tutorial.entity.Slot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SlotRepositority extends JpaRepository<Slot,Long> {
}
