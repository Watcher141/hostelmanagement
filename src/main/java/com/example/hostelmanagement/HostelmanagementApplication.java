package com.example.hostelmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.hostelmanagement.config.DotenvConfig;

@SpringBootApplication
public class HostelmanagementApplication {

	public static void main(String[] args) {

		// Load runtime environment variables from .env (if present)
		DotenvConfig.load();

		SpringApplication.run(HostelmanagementApplication.class, args);
	}

}
