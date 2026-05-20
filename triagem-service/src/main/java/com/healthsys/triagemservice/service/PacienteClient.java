package com.healthsys.triagemservice.service;

import com.healthsys.triagemservice.client.PacienteFeignClient;
import com.healthsys.triagemservice.dto.AlergiaDTO;
import com.healthsys.triagemservice.dto.ComorbidadeDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<AlergiaDTO> getAlergiasPorPaciente(UUID pacienteId) {
        ResponseEntity<List<AlergiaDTO>> response = feign.getAlergiasPorPaciente(pacienteId);
        return response.getBody() != null ? response.getBody() : List.of();
    }

    public List<ComorbidadeDTO> getComorbidadesPorPaciente(UUID pacienteId) {
        ResponseEntity<List<ComorbidadeDTO>> response = feign.getComorbidadesPorPaciente(pacienteId);
        return response.getBody() != null ? response.getBody() : List.of();
    }
}