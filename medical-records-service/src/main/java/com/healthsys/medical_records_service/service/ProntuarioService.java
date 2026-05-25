package com.healthsys.medical_records_service.service;

import com.healthsys.medical_records_service.client.BedClient;
import com.healthsys.medical_records_service.model.Prontuario;
import com.healthsys.medical_records_service.repository.ProntuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProntuarioService {

    @Autowired
    private ProntuarioRepository prontuarioRepository;

    @Autowired
    private BedClient bedClient;

    public Prontuario internarPaciente(String pacienteId, Long leitoId, String diagnostico) {
        Prontuario prontuario = new Prontuario();
        prontuario.setPacienteId(pacienteId);
        prontuario.setLeitoId(leitoId);
        prontuario.setHistoricoClinico("Internação registrada: " + diagnostico);
        prontuario.setStatus("INTERNADO");
        
        Prontuario salvo = prontuarioRepository.save(prontuario);

        // Comunicação síncrona: se o leito estiver ocupado, o bed-service joga uma exceção,
        // interrompendo o fluxo e impedindo inconsistências.
        bedClient.internarPaciente(leitoId, pacienteId);

        return salvo;
    }

    public Prontuario darAltaPaciente(String prontuarioId) {
        Prontuario prontuario = prontuarioRepository.findById(prontuarioId)
                .orElseThrow(() -> new RuntimeException("Prontuário não encontrado"));

        if (!"INTERNADO".equals(prontuario.getStatus())) {
            throw new RuntimeException("Paciente não consta como internado.");
        }

        if (prontuario.getLeitoId() != null) {
            bedClient.liberarLeito(prontuario.getLeitoId());
        }

        prontuario.setStatus("ALTA");
        prontuario.setHistoricoClinico(prontuario.getHistoricoClinico() + " | Alta concedida pelo médico.");
        
        return prontuarioRepository.save(prontuario);
    }

    // Métodos novos adicionados abaixo:

    public List<Prontuario> listarTodosProntuarios() {
        return prontuarioRepository.findAll();
    }

    public Prontuario buscarProntuarioPorId(String id) {
        return prontuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prontuário não encontrado com o ID: " + id));
    }

    public List<Prontuario> buscarPronutariosPorStatus(String status) {
        // Nota: Garanta que seu ProntuarioRepository tenha o método findByStatus(String status)
        return prontuarioRepository.findByStatus(status);
    }
}