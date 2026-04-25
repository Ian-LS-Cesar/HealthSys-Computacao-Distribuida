package com.healthsys.triagemservice.service;

import com.healthsys.triagemservice.dto.RiscoRequestDTO;
import com.healthsys.triagemservice.dto.RiscoResponseDTO;
import com.healthsys.triagemservice.mapper.RiscoMapper;
import com.healthsys.triagemservice.model.Risco;
import com.healthsys.triagemservice.repository.RiscoRepository;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Setter
@Service
public class RiscoService {
    private final RiscoRepository riscoRepository;

    public RiscoService(RiscoRepository riscoRepository) {
        this.riscoRepository = riscoRepository;
    }

    public List<RiscoResponseDTO> getRiscos(){
        List<Risco> riscos = riscoRepository.findAll();
        return riscos.stream()
                .map(RiscoMapper::toDTO)
                .toList();
    }

    public RiscoResponseDTO criarRisco(RiscoRequestDTO dto){
        Risco novoRisco = RiscoMapper.toModel(dto);
        return RiscoMapper.toDTO(riscoRepository.save(novoRisco));
    }

    public RiscoResponseDTO atualizarRisco(Integer id, RiscoRequestDTO dto){
        Risco risco = riscoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Risco não encontrado com ID: " + id));

        risco.setDescricao(dto.getDescricao());
        return RiscoMapper.toDTO(riscoRepository.save(risco));

    }

    public void deletarRisco(Integer id){
        riscoRepository.deleteById(id);
    }
}
