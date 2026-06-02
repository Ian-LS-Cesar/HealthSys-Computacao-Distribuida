package com.healthsys.pacienteservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class PacienteServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PacienteServiceApplication.class, args);
    }

}
