package com.healthsys.triagemservice.service;

import com.healthsys.triagemservice.dto.TriagemRequestDTO;
import com.healthsys.triagemservice.dto.TriagemResponseDTO;
import com.healthsys.triagemservice.mapper.TriagemMapper;
import com.healthsys.triagemservice.model.Risco;
import com.healthsys.triagemservice.model.Status;
import com.healthsys.triagemservice.model.Triagem;
import com.healthsys.triagemservice.repository.RiscoRepository;
import com.healthsys.triagemservice.repository.StatusRepository;
import com.healthsys.triagemservice.repository.TriagemRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;
import java.util.UUID;

@Service
public class TriagemService {
    private final TriagemRepository triagemRepository;
    private final RiscoRepository riscoRepository;
    private final StatusRepository statusRepository;
    private final PacienteClient pacienteClient;

    public TriagemService(TriagemRepository triagemRepository, RiscoRepository riscoRepository, StatusRepository statusRepository, PacienteClient pacienteClient) {
        this.triagemRepository = triagemRepository;
        this.riscoRepository = riscoRepository;
        this.statusRepository = statusRepository;
        this.pacienteClient = pacienteClient;
    }

    public List<TriagemResponseDTO> getTriagens(){
        return triagemRepository.findAll()
                .stream()
                .map(TriagemMapper::toDTO)
                .toList();
    }

    public TriagemResponseDTO getTriagemById(UUID id){
        Triagem triagem = triagemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Triagem não encontrada com ID: " + id));
        return TriagemMapper.toDTO(triagem);
    }

    public List<TriagemResponseDTO> getTriagemByPaciente(UUID idPaciente){
        validarPaciente(idPaciente);

        return triagemRepository.findByPaciente(idPaciente)
                .stream()
                .map(TriagemMapper::toDTO)
                .toList();
    }

    public TriagemResponseDTO criarTriagem(TriagemRequestDTO triagemRequestDTO){
        validarPaciente(triagemRequestDTO.getPaciente());

        Risco risco = riscoRepository.findById(triagemRequestDTO.getRisco())
                .orElseThrow(() -> new IllegalArgumentException("Risco não encontrado com ID: " + triagemRequestDTO.getRisco()));

        Status status = statusRepository.findById(triagemRequestDTO.getStatus())
                .orElseThrow(() -> new IllegalArgumentException("Status não encontrado com ID: " + triagemRequestDTO.getStatus()));

        Triagem novaTriagem = TriagemMapper.toModel(triagemRequestDTO, risco, status);
        return TriagemMapper.toDTO(triagemRepository.save(novaTriagem));
    }

    public TriagemResponseDTO atualizarTriagem(UUID id, TriagemRequestDTO triagemRequestDTO){
        Triagem triagem = triagemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Triagem não encontrada com ID: " + id));

        validarPaciente(triagemRequestDTO.getPaciente());

        Risco risco = riscoRepository.findById(triagemRequestDTO.getRisco())
                .orElseThrow(() -> new IllegalArgumentException("Risco não encontrado com ID: " + triagemRequestDTO.getRisco()));

        Status status = statusRepository.findById(triagemRequestDTO.getStatus())
                .orElseThrow(() -> new IllegalArgumentException("Status não encontrado com ID: " + triagemRequestDTO.getStatus()));

        triagem.setPaciente(triagemRequestDTO.getPaciente());
        triagem.setRisco(risco);
        triagem.setStatus(status);
        return TriagemMapper.toDTO(triagemRepository.save(triagem));
    }
    public void deletarTriagem(UUID id){
        triagemRepository.deleteById(id);
    }

    private void validarPaciente(UUID idPaciente){
        try {
            boolean existe = pacienteClient.existePaciente(idPaciente);
            if (!existe){
                throw new IllegalArgumentException("Paciente não encontrado com ID: " + idPaciente);
            }
        } catch (WebClientResponseException e){
            throw new IllegalArgumentException("Erro ao verificar paciente com ID: " + idPaciente, e);
        }
    }

}
