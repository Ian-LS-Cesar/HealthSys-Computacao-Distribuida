package com.healthsys.triagemservice.service;

import com.healthsys.triagemservice.client.PacienteFeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PacienteClient {
    private final PacienteFeignClient feign;

    public PacienteClient(PacienteFeignClient feign) {
        this.feign = feign;
    }

    public boolean existePaciente(UUID id) {
        try {
            ResponseEntity<Void> rest = feign.existePaciente(id);
            return rest.getStatusCode().is2xxSuccessful();
        } catch (feign.FeignException e) {
            if (e.status() == 404) {
                return false;
            }
            throw new IllegalStateException("Erro ao chamar paciente-service", e);
        }
    }
}