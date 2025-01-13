package com.example.tutorial.repository;

import com.example.tutorial.entity.TurfDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TurfRepository extends JpaRepository<TurfDetails,Long> {

}
