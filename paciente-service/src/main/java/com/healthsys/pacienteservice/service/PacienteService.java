package com.healthsys.pacienteservice.service;

import com.healthsys.pacienteservice.dto.EnderecoRequestDTO;
import com.healthsys.pacienteservice.dto.PacienteRequestDTO;
import com.healthsys.pacienteservice.dto.PacienteResponseDTO;
import com.healthsys.pacienteservice.exception.CpfAlreadyExistsException;
import com.healthsys.pacienteservice.exception.PacienteNotFoundException;
import com.healthsys.pacienteservice.mapper.PacienteMapper;
import com.healthsys.pacienteservice.model.*;
import com.healthsys.pacienteservice.repository.GeneroRepository;
import com.healthsys.pacienteservice.repository.PacienteRepository;
import com.healthsys.pacienteservice.repository.SexoRepository;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Setter
@Service
public class PacienteService {
    private final PacienteRepository pacienteRepository;
    private final GeneroRepository generoRepository;
    private final SexoRepository sexoRepository;

    public PacienteService(
            PacienteRepository pacienteRepository,
            GeneroRepository generoRepository,
            SexoRepository sexoRepository
    ) {
        this.pacienteRepository = pacienteRepository;
        this.generoRepository = generoRepository;
        this.sexoRepository = sexoRepository;
    }

    public List<PacienteResponseDTO> getPacientes() {
        List<Paciente> pacientes = pacienteRepository.findAll();
        return pacientes.stream().map(PacienteMapper::toDTO).toList();
    }

    private List<Telefone> mapTelefones(List<String> numeros, Paciente paciente) {
        if (numeros == null) return new ArrayList<>();
        return numeros.stream()
                .filter(n -> n != null && !n.isBlank())
                .map(n -> {
                    Telefone telefone = new Telefone();
                    telefone.setNumero(n.trim());
                    telefone.setPaciente(paciente);
                    return telefone;
                })
                .toList();
    }

    private List<Endereco> mapEnderecos(List<EnderecoRequestDTO> enderecosDTO, Paciente paciente) {
        if (enderecosDTO == null) return new ArrayList<>();
        return enderecosDTO.stream().map(dto -> {
            Endereco endereco = new Endereco();
            endereco.setLogradouro(dto.getLogradouro().trim());
            endereco.setNumero(dto.getNumero().trim());
            endereco.setComplemento(dto.getComplemento() == null ? null : dto.getComplemento().trim());
            endereco.setBairro(dto.getBairro().trim());
            endereco.setCep(dto.getCep().trim());
            endereco.setCidade(dto.getCidade().trim());
            endereco.setUf(dto.getUf().trim());
            endereco.setPaciente(paciente);
            return endereco;
        }).toList();
    }
    private List<Alergia> mapAlergias(List<String> descricoes, Paciente paciente) {
        if (descricoes == null) return new ArrayList<>();
        return descricoes.stream()
                .filter(d -> d != null && !d.isBlank())
                .map(d -> {
                    Alergia alergia = new Alergia();
                    alergia.setDescricao(d.trim());
                    alergia.setPaciente(paciente);
                    return alergia;
                })
                .toList();
    }

    public PacienteResponseDTO criarPaciente(PacienteRequestDTO dto) {
        if (pacienteRepository.existsByCpf(dto.getCpf())) {
            throw new CpfAlreadyExistsException("Já existe um paciente com esse CPF");
        }

        Genero genero = generoRepository.findById(dto.getGenero())
                .orElseThrow(() -> new IllegalArgumentException("Gênero não encontrado com o ID: " + dto.getGenero()));
        Sexo sexo = sexoRepository.findById(dto.getSexo())
                .orElseThrow(() -> new IllegalArgumentException("Sexo não encontrado com o ID: " + dto.getSexo()));

        Paciente novoPaciente = PacienteMapper.toModel(dto, genero, sexo);
        novoPaciente.setTelefones(mapTelefones(dto.getTelefones(), novoPaciente));
        novoPaciente.setAlergias(mapAlergias(dto.getAlergias(), novoPaciente));
        novoPaciente.setEnderecos(mapEnderecos(dto.getEnderecos(), novoPaciente));

        return PacienteMapper.toDTO(pacienteRepository.save(novoPaciente));
    }

    public PacienteResponseDTO atualizarPaciente(UUID id, PacienteRequestDTO dto) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new PacienteNotFoundException("Paciente não encontrado com ID: " + id));

        if (pacienteRepository.existsByCpfAndIdNot(dto.getCpf(), id)) {
            throw new IllegalArgumentException("Já existe um paciente com esse CPF");
        }

        Genero genero = generoRepository.findById(dto.getGenero())
                .orElseThrow(() -> new IllegalArgumentException("Gênero não encontrado com o ID: " + dto.getGenero()));
        Sexo sexo = sexoRepository.findById(dto.getSexo())
                .orElseThrow(() -> new IllegalArgumentException("Sexo não encontrado com o ID: " + dto.getSexo()));

        paciente.setNome(dto.getNome());
        paciente.setNomeSocial(dto.getNomeSocial());
        paciente.setDataNascimento(LocalDate.parse(dto.getDataNascimento()));
        paciente.setGenero(genero);
        paciente.setSexo(sexo);
        paciente.setCpf(dto.getCpf());

        paciente.getTelefones().clear();
        paciente.getTelefones().addAll(mapTelefones(dto.getTelefones(), paciente));

        paciente.getAlergias().clear();
        paciente.getAlergias().addAll(mapAlergias(dto.getAlergias(), paciente));

        paciente.getEnderecos().clear();
        paciente.getEnderecos().addAll(mapEnderecos(dto.getEnderecos(), paciente));

        return PacienteMapper.toDTO(pacienteRepository.save(paciente));
    }

    public void deletarPaciente(UUID id){
        pacienteRepository.deleteById(id);
    }
}
