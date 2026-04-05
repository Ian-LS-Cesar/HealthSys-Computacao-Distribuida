package com.healthsys.pacientes.service;

import com.healthsys.pacientes.dto.TelefoneResponseDTO;
import com.healthsys.pacientes.mapper.TelefoneMapper;
import com.healthsys.pacientes.model.Telefone;
import com.healthsys.pacientes.repository.TelefoneRepository;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Setter
@Service
public class TelefoneService {
    private final TelefoneRepository telefoneRepository;

    public TelefoneService(TelefoneRepository telefoneRepository) {
        this.telefoneRepository = telefoneRepository;
    }

    public List<TelefoneResponseDTO> getTelefones() {
        List<Telefone> telefones = telefoneRepository.findAll();
        return telefones.stream()
                .map(TelefoneMapper::toDTO)
                .toList();
    }
}
