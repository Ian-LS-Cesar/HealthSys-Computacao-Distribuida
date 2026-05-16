package com.healthsys.triagemservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class TriagemServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TriagemServiceApplication.class, args);
    }

}
