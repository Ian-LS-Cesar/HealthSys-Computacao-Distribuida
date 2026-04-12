package com.healthsys.pacienteservice.service;

import com.healthsys.pacienteservice.dto.SexoRequestDTO;
import com.healthsys.pacienteservice.dto.SexoResponseDTO;
import com.healthsys.pacienteservice.mapper.SexoMapper;
import com.healthsys.pacienteservice.model.Sexo;
import com.healthsys.pacienteservice.repository.SexoRepository;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Setter
@Service
public class SexoService {
    private final SexoRepository sexoRepository;

    public SexoService(SexoRepository sexoRepository) {
        this.sexoRepository = sexoRepository;
    }

    public List<SexoResponseDTO> getSexos() {
        List<Sexo> sexos = sexoRepository.findAll();
        return sexos.stream()
                .map(SexoMapper::toDTO)
                .toList();
    }

    public SexoResponseDTO criarSexo(SexoRequestDTO dto) {
        Sexo novoSexo = SexoMapper.toModel(dto);
        return SexoMapper.toDTO(sexoRepository.save(novoSexo));
    }

    public SexoResponseDTO atualizarSexo(Integer id, SexoRequestDTO dto) {
        Sexo sexo = sexoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Sexo não encontrado com ID: " + id));

        sexo.setDescricao(dto.getDescricao());
        return SexoMapper.toDTO(sexoRepository.save(sexo));
    }

    public void deletarSexo(Integer id) {
        sexoRepository.deleteById(id);
    }
}
