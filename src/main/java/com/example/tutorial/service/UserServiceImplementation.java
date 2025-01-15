package com.example.tutorial.service;

import com.example.tutorial.entity.CacheNames;
import com.example.tutorial.entity.User;
import com.example.tutorial.exception.UserNotFound;
import com.example.tutorial.repository.UserRepositority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImplementation implements UserService{

    @Autowired
    UserRepositority userRepo;

    @Override
    @Cacheable(value = CacheNames.ALL_USERS_CACHE)
    public List<User> fetchUserList(){
        return userRepo.findAll();
    }

    @Override

    public User save(User user){
        userRepo.save(user);
        return user;
    }

    @Override
    @Cacheable(key = "#id",value = CacheNames.USERS_CACHE)
    public User findUserById(long id) throws UserNotFound {
        User user=userRepo.findById(id).orElse(null);
        if(user==null) throw new UserNotFound("user not found");
        return user;
    }

    @Override
    @CacheEvict(key = "#id", value = CacheNames.USERS_CACHE)
    public boolean deleteUserById(long id) {
        if (userRepo.existsById(id)) {
            userRepo.deleteById(id);
            return true;  // User deleted
        } else {
            return false;  // User not found
        }
    }

    @Override
    @CachePut(key = "#id", value = CacheNames.USERS_CACHE)
    public User updateUserById(long id, User user) {
        // Fetch the existing user by ID
        User userDetail = userRepo.findById(id).orElse(null);

        // Check if the user exists
        if (userDetail != null) {
            // Update userName if new value is provided and non-empty
            if (Objects.nonNull(user.getUserName()) && !"".equalsIgnoreCase(user.getUserName())) {
                userDetail.setUserName(user.getUserName());
            }

            // Update address if new value is provided and non-empty
            if (Objects.nonNull(user.getEmailId()) && !"".equalsIgnoreCase(user.getEmailId())) {
                userDetail.setEmailId(user.getEmailId());  // Corrected this line
            }

            // update phoneNumber if new value is provided and non-empty
            if (Objects.nonNull(user.getPhoneNumber()) && !"".equalsIgnoreCase(user.getPhoneNumber())) {
                userDetail.setPhoneNumber(user.getPhoneNumber());  // Added phone number update
            }

            // Save the updated user
            return userRepo.save(userDetail);
        } else {
            // Handle case where user is not found (Optional)
            return null;  // Or throw an exception if you prefer
        }
    }

}
