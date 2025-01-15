package com.example.tutorial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TurfManagementApplication {

	public static void main(String[] args) {

		SpringApplication.run(TurfManagementApplication.class, args);

	}

}
