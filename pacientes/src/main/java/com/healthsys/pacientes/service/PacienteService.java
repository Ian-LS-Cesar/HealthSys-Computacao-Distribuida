package com.healthsys.pacientes.service;

import com.healthsys.pacientes.dto.PacienteRequestDTO;
import com.healthsys.pacientes.dto.PacienteResponseDTO;
import com.healthsys.pacientes.mapper.PacienteMapper;
import com.healthsys.pacientes.model.Genero;
import com.healthsys.pacientes.model.Paciente;
import com.healthsys.pacientes.model.Sexo;
import com.healthsys.pacientes.repository.GeneroRepository;
import com.healthsys.pacientes.repository.PacienteRepository;
import com.healthsys.pacientes.repository.SexoRepository;
import lombok.Setter;
import org.springframework.stereotype.Service;
import java.util.List;

@Setter
@Service
public class PacienteService {
    private final PacienteRepository pacienteRepository;
    private final GeneroRepository generoRepository;
    private final SexoRepository sexoRepository;


    public PacienteService(PacienteRepository pacienteRepository, GeneroRepository generoRepository, SexoRepository sexoRepository) {
        this.pacienteRepository = pacienteRepository;
        this.generoRepository = generoRepository;
        this.sexoRepository = sexoRepository;
    }

    public List<PacienteResponseDTO> getPacientes(){
        List<Paciente> pacientes = pacienteRepository.findAll();
        return pacientes.stream()
                .map(PacienteMapper::toDTO).toList();
    }

    public PacienteResponseDTO criarPaciente(PacienteRequestDTO pacienteRequestDTO){

        Genero genero = generoRepository.findById(pacienteRequestDTO.getGenero())
                .orElseThrow(() -> new IllegalArgumentException("Gênero não encontrado com o ID: " + pacienteRequestDTO.getGenero()));

        Sexo sexo = sexoRepository.findById(pacienteRequestDTO.getSexo())
                .orElseThrow(() -> new IllegalArgumentException("Sexo não encontrado com o ID: " + pacienteRequestDTO.getSexo()));

        Paciente novoPaciente = pacienteRepository.save(PacienteMapper.toModel(pacienteRequestDTO,genero,sexo));

        Paciente pacienteSalvo = pacienteRepository.save(novoPaciente);

        return PacienteMapper.toDTO(pacienteSalvo);
    }
}
