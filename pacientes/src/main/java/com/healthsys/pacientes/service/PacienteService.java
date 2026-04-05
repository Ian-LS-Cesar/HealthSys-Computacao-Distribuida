package com.healthsys.pacientes.service;

import com.healthsys.pacientes.dto.PacienteResponseDTO;
import com.healthsys.pacientes.mapper.PacienteMapper;
import com.healthsys.pacientes.model.Paciente;
import com.healthsys.pacientes.repository.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PacienteService {
    private PacienteRepository pacienteRepository;

    public void setPacienteRepository(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public List<PacienteResponseDTO> getPacientes(){
        List<Paciente> pacientes = pacienteRepository.findAll();
        return pacientes.stream()
                .map(PacienteMapper::toDTO).toList();
    }
}
