package com.healthsys.pacientes.service;

import com.healthsys.pacientes.dto.PacienteRequestDTO;
import com.healthsys.pacientes.dto.PacienteResponseDTO;
import com.healthsys.pacientes.exception.PacienteNotFoundException;
import com.healthsys.pacientes.mapper.PacienteMapper;
import com.healthsys.pacientes.model.Genero;
import com.healthsys.pacientes.model.Paciente;
import com.healthsys.pacientes.model.Sexo;
import com.healthsys.pacientes.model.Telefone;
import com.healthsys.pacientes.repository.GeneroRepository;
import com.healthsys.pacientes.repository.PacienteRepository;
import com.healthsys.pacientes.repository.SexoRepository;
import com.healthsys.pacientes.repository.TelefoneRepository;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Setter
@Service
public class PacienteService {
    private final PacienteRepository pacienteRepository;
    private final GeneroRepository generoRepository;
    private final SexoRepository sexoRepository;
    private final TelefoneRepository telefoneRepository;

    public PacienteService(
            PacienteRepository pacienteRepository,
            GeneroRepository generoRepository,
            SexoRepository sexoRepository,
            TelefoneRepository telefoneRepository
    ) {
        this.pacienteRepository = pacienteRepository;
        this.generoRepository = generoRepository;
        this.sexoRepository = sexoRepository;
        this.telefoneRepository = telefoneRepository;
    }

    public List<PacienteResponseDTO> getPacientes() {
        List<Paciente> pacientes = pacienteRepository.findAll();
        return pacientes.stream().map(PacienteMapper::toDTO).toList();
    }

    public PacienteResponseDTO criarPaciente(PacienteRequestDTO pacienteRequestDTO) {
        Genero genero = generoRepository.findById(pacienteRequestDTO.getGenero())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Gênero não encontrado com o ID: " + pacienteRequestDTO.getGenero()));

        Sexo sexo = sexoRepository.findById(pacienteRequestDTO.getSexo())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Sexo não encontrado com o ID: " + pacienteRequestDTO.getSexo()));

        Telefone telefone = telefoneRepository.findById(pacienteRequestDTO.getTelefone())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Telefone não encontrado com o ID: " + pacienteRequestDTO.getTelefone()));

        Paciente novoPaciente = PacienteMapper.toModel(pacienteRequestDTO, genero, sexo, telefone);
        Paciente pacienteSalvo = pacienteRepository.save(novoPaciente);

        return PacienteMapper.toDTO(pacienteSalvo);
    }

    public PacienteResponseDTO atualizarPaciente(UUID id, PacienteRequestDTO pacienteRequestDTO) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new PacienteNotFoundException("Paciente não encontrado com ID: " + id));

        if (pacienteRepository.existsByCpfAndIdNot(pacienteRequestDTO.getCpf(), id)) {
            throw new IllegalArgumentException("Já existe um paciente com esse CPF");
        }


        Genero genero = generoRepository.findById(pacienteRequestDTO.getGenero())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Gênero não encontrado com o ID: " + pacienteRequestDTO.getGenero()));

        Sexo sexo = sexoRepository.findById(pacienteRequestDTO.getSexo())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Sexo não encontrado com o ID: " + pacienteRequestDTO.getSexo()));

        Telefone telefone = telefoneRepository.findById(pacienteRequestDTO.getTelefone())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Telefone não encontrado com o ID: " + pacienteRequestDTO.getTelefone()));

        paciente.setNome(pacienteRequestDTO.getNome());
        paciente.setNomeSocial(pacienteRequestDTO.getNomeSocial());
        paciente.setDataNascimento(LocalDate.parse(pacienteRequestDTO.getDataNascimento()));
        paciente.setGenero(genero);
        paciente.setSexo(sexo);
        paciente.setTelefone(telefone);
        paciente.setCpf(pacienteRequestDTO.getCpf());

        Paciente pacienteAtualizado = pacienteRepository.save(paciente);
        return PacienteMapper.toDTO(pacienteAtualizado);
    }

    public void deletarPaciente(UUID id){
        pacienteRepository.deleteById(id);
    }
}
