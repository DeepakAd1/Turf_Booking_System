package com.example.tutorial.service;

import com.example.tutorial.entity.TurfDetails;
import com.example.tutorial.exception.UserNotFound;
import com.example.tutorial.repository.TurfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class TurfServiceImplementation implements TurfService{
    
    @Autowired
    private TurfRepository turfRepo;
    
    @Override
    @Cacheable(key = "allTurfs",value = "ALL_TURFS_CACHE")
    public List<TurfDetails> fetchTurfList(){
        return turfRepo.findAll();
    }
    
    @Override
    @Cacheable(key = "#id", value = "TURF_CACHE")
    public TurfDetails findTurfById(long id) throws UserNotFound {
        TurfDetails turf=turfRepo.findById(id).orElse(null);
        if(turf==null) throw new UserNotFound("user not found");
        return turf;
    }
    
    @Override
    public TurfDetails save(TurfDetails turfDetails){
        turfRepo.save(turfDetails);
        return turfDetails;
    }
    
    @Override
    @CacheEvict(key = "#id", value = "TURF_CACHE")
    public boolean deleteTurfById(long id){
        if (turfRepo.existsById(id)) {
            turfRepo.deleteById(id);
            return true;  // User deleted
        } else {
            return false;  // User not found
        }
    }
    
    @Override
    @CachePut(key = "#id", value = "TURF_CACHE")
    public TurfDetails updateTurfById(long id, TurfDetails turfDetails){
        TurfDetails turf = turfRepo.findById(id).orElse(null);

        // Check if the user exists
        if (turf != null) {
            // Update userName if new value is provided and non-empty
            if (Objects.nonNull(turfDetails.getTurfName()) && !"".equalsIgnoreCase(turfDetails.getTurfName())) {
                turf.setTurfName(turfDetails.getTurfName());
            }

            // Update address if new value is provided and non-empty
            if (Objects.nonNull(turfDetails.getEmailId()) && !"".equalsIgnoreCase(turfDetails.getEmailId())) {
                turf.setEmailId(turfDetails.getEmailId());  // Corrected this line
            }

            // update phoneNumber if new value is provided and non-empty
            if (Objects.nonNull(turfDetails.getTurfLocation()) && !"".equalsIgnoreCase(turfDetails.getTurfLocation())) {
                turf.setTurfLocation(turfDetails.getTurfLocation());  // Added phone number update
            }

            // Save the updated user
            return turfRepo.save(turf);
        } else {
            // Handle case where user is not found (Optional)
            return null;  // Or throw an exception if you prefer
        }
    }
}
