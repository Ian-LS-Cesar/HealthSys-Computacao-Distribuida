package com.healthsys.bed_service.service;

import com.healthsys.bed_service.model.Leito;
import com.healthsys.bed_service.model.StatusLeito;
import com.healthsys.bed_service.repository.LeitoRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class LeitoService {

    @Autowired
    private LeitoRepository leitoRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private static final String EXCHANGE_NAME = "medical.notification.exchange";

    public Leito internarPaciente(Long leitoId, String pacienteId) {
        Leito leito = leitoRepository.findById(leitoId)
                .orElseThrow(() -> new RuntimeException("Leito não encontrado"));

        if (leito.getStatus() != StatusLeito.LIVRE) {
            throw new RuntimeException("Leito já está ocupado ou em manutenção");
        }

        leito.setStatus(StatusLeito.OCUPADO);
        leito.setPacienteId(pacienteId);
        Leito leitoAtualizado = leitoRepository.save(leito);

        // Disparar evento para o RabbitMQ (Roteamento por ala/especialidade)
        String routingKey = "atendimento." + leito.getAla().toLowerCase();
        Map<String, String> payload = Map.of(
            "recipient", "Ala de " + leito.getAla(),
            "message", "Novo paciente internado no leito " + leito.getCodigo()
        );
        
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, routingKey, payload);

        return leitoAtualizado;
    }

    public Leito liberarLeito(Long leitoId) {
        Leito leito = leitoRepository.findById(leitoId)
                .orElseThrow(() -> new RuntimeException("Leito não encontrado"));

        leito.setStatus(StatusLeito.HIGIENIZACAO);
        leito.setPacienteId(null);
        return leitoRepository.save(leito);
    }
}