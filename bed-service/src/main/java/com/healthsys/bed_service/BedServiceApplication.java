package com.healthsys.bed_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class BedServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BedServiceApplication.class, args);
	}

}
