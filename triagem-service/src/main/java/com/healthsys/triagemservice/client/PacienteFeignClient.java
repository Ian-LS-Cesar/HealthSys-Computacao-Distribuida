package com.healthsys.triagemservice.client;

import com.healthsys.triagemservice.dto.AlergiaDTO;
import com.healthsys.triagemservice.dto.ComorbidadeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "paciente-service")
public interface PacienteFeignClient {
    @GetMapping("/pacientes/{id}")
    ResponseEntity<Void> existePaciente(@PathVariable UUID id);

    @GetMapping("/alergias/paciente/{pacienteId}")
    ResponseEntity<List<AlergiaDTO>> getAlergiasPorPaciente(@PathVariable UUID pacienteId);

    @GetMapping("/comorbidades/paciente/{pacienteId}")
    ResponseEntity<List<ComorbidadeDTO>> getComorbidadesPorPaciente(@PathVariable UUID pacienteId);
}
