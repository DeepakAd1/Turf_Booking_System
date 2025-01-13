package com.example.tutorial.repository;

import com.example.tutorial.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositority extends JpaRepository<User,Long> {

//    @Query(value = "",nativeQuery = true)
//    public void update();
}
