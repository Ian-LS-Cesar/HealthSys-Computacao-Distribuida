package com.healthsys.pacientes.service;

import com.healthsys.pacientes.dto.SexoResponseDTO;
import com.healthsys.pacientes.mapper.SexoMapper;
import com.healthsys.pacientes.model.Sexo;
import com.healthsys.pacientes.repository.SexoRepository;
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

    public List<SexoResponseDTO> getGeneros(){
        List<Sexo> sexos = sexoRepository.findAll();
        return sexos.stream()
                .map(SexoMapper::toDTO)
                .toList();
    }
}
