package com.healthsys.pacientes.service;

import com.healthsys.pacientes.dto.GeneroResponseDTO;
import com.healthsys.pacientes.mapper.GeneroMapper;
import com.healthsys.pacientes.model.Genero;
import com.healthsys.pacientes.repository.GeneroRepository;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Setter
@Service
public class GeneroService {
    private final GeneroRepository generoRepository;

    public GeneroService(GeneroRepository generoRepository) {
        this.generoRepository = generoRepository;
    }

    public List<GeneroResponseDTO> getGeneros(){
        List<Genero> generos = generoRepository.findAll();
        return generos.stream()
                .map(GeneroMapper::toDTO)
                .toList();
    }
}
