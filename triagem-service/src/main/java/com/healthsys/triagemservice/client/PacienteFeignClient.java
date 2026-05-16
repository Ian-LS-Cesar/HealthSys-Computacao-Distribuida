package com.healthsys.triagemservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "paciente-service")
public interface PacienteFeignClient {
    @GetMapping("/pacientes/{id}")
    ResponseEntity<Void> existePaciente(@PathVariable("id") UUID id);
}
