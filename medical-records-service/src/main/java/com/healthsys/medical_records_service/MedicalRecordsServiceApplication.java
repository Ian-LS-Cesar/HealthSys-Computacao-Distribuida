package com.healthsys.medical_records_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients // Habilita a varredura das interfaces @FeignClient
public class MedicalRecordsServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(MedicalRecordsServiceApplication.class, args);
    }
}