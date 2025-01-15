package com.example.tutorial.service;


import com.example.tutorial.entity.User;
import com.example.tutorial.exception.UserNotFound;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    public List<User> fetchUserList();

    public User save(User user);

    User findUserById(long id) throws UserNotFound;

    public boolean deleteUserById(long id);

    public User updateUserById(long id,User user);
}
