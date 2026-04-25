package com.healthsys.triagemservice.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.UUID;

@Service
public class PacienteClient {
    private final WebClient webClient;

    public PacienteClient(WebClient.Builder builder,
                          @Value("${services.paciente.base-url}") String pacienteBaseUrl) {
        this.webClient = builder.baseUrl(pacienteBaseUrl).build();
    }

    public boolean existePaciente(UUID pacienteId) {
        try {
            webClient.get()
                    .uri("/pacientes/{id}", pacienteId)
                    .retrieve()
                    .toBodilessEntity()
                    .block();
            return true;
        } catch (WebClientResponseException.NotFound ex) {
            return false;
        }
    }
}