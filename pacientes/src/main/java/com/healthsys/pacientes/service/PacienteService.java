package com.healthsys.pacientes.service;

import com.healthsys.pacientes.dto.PacienteResponseDTO;
import com.healthsys.pacientes.mapper.PacienteMapper;
import com.healthsys.pacientes.model.Paciente;
import com.healthsys.pacientes.repository.PacienteRepository;
import lombok.Setter;
import org.springframework.stereotype.Service;
import java.util.List;

@Setter
@Service
public class PacienteService {
    private final PacienteRepository pacienteRepository;

    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public List<PacienteResponseDTO> getPacientes(){
        List<Paciente> pacientes = pacienteRepository.findAll();
        return pacientes.stream()
                .map(PacienteMapper::toDTO).toList();
    }
}
