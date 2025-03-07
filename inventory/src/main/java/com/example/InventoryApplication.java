package com.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InventoryApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(InventoryApplication.class, args);
	}
	
	public void run(String... args) throws Exception {
		System.err.println("The app is running");
	}

}
