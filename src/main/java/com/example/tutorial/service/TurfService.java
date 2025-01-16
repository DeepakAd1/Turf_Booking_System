package com.example.tutorial.service;

import com.example.tutorial.entity.TurfDetails;
import com.example.tutorial.exception.UserNotFound;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;


public interface TurfService {
    public List<TurfDetails> fetchTurfList();

    public TurfDetails findTurfById(long id) throws UserNotFound;

    public TurfDetails save(TurfDetails turfDetails);

    public boolean deleteTurfById(long id);

    public TurfDetails updateTurfById(long id, TurfDetails turfDetails);
}
