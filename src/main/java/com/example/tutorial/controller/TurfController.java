package com.example.tutorial.controller;

import com.example.tutorial.entity.TurfDetails;
import com.example.tutorial.exception.UserNotFound;
import com.example.tutorial.service.TurfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TurfController {
    @Autowired
    private TurfService turfService;

    @GetMapping("/getTurfs")
    @ResponseBody
    public List<TurfDetails> getTurfs(){
        return turfService.fetchTurfList();
    }

    @GetMapping("/getTurf/{id}")
    public ResponseEntity<TurfDetails> getTurfById(@PathVariable("id") long id) throws UserNotFound {
        return ResponseEntity.ok(turfService.findTurfById(id));
    }

    @PostMapping("/addTurf")
    @ResponseBody
    public TurfDetails addTurf(@RequestBody TurfDetails turfDetails){
        return turfService.save(turfDetails);
    }

    @DeleteMapping("/deleteTurf/{id}")
    public ResponseEntity<String> deleteTurf(@PathVariable("id") long id) {
        boolean deleted = turfService.deleteTurfById(id); // delete the user

        if (deleted) {
            return ResponseEntity.ok("Turf deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Turf not found");
        }
    }

    @PutMapping("/updateTurf/{id}")
    @ResponseBody
    public TurfDetails updateUser(@PathVariable("id")long id,@RequestBody TurfDetails turfDetails){
        return turfService.updateTurfById(id,turfDetails);
    }

}
