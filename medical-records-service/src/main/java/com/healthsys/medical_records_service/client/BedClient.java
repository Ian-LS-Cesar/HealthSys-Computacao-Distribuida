package com.healthsys.medical_records_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "bed-service", path = "/bed/leitos")
public interface BedClient {

    @PostMapping("/{id}/internar")
    void internarPaciente(@PathVariable("id") Long leitoId, @RequestParam("pacienteId") String pacienteId);

    @PostMapping("/{id}/liberar")
    void liberarLeito(@PathVariable("id") Long leitoId);
}