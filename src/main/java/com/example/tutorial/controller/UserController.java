package com.example.tutorial.controller;


import com.example.tutorial.entity.User;
import com.example.tutorial.exception.UserNotFound;
import com.example.tutorial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/getUser")
    @ResponseBody
    public List<User> getUser(){
        return userService.fetchUserList();
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long id) throws UserNotFound {
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @PostMapping("/addUser")
    @ResponseBody
    public User addUser(@RequestBody User user){
        return userService.save(user);
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") long id) {
        boolean deleted = userService.deleteUserById(id); // delete the user

        if (deleted) {
            return ResponseEntity.ok("User deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    @PutMapping("/updateUser/{id}")
    @ResponseBody
    public User updateUser(@PathVariable("id")long id,@RequestBody User user){
        return userService.updateUserById(id,user);
    }

}
