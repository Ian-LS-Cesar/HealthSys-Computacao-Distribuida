package com.healthsys.pacienteservice.service;

import com.healthsys.pacienteservice.dto.ComorbidadeRequestDTO;
import com.healthsys.pacienteservice.dto.ComorbidadeResponseDTO;
import com.healthsys.pacienteservice.mapper.ComorbidadeMapper;
import com.healthsys.pacienteservice.model.Comorbidade;
import com.healthsys.pacienteservice.repository.ComorbidadeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComorbidadeService {
    public final ComorbidadeRepository comorbidadeRepository;

    public ComorbidadeService(ComorbidadeRepository comorbidadeRepository) {
        this.comorbidadeRepository = comorbidadeRepository;
    }

    public List<ComorbidadeResponseDTO> getComorbidades(){
        List<Comorbidade> comorbidades = comorbidadeRepository.findAll();
        return comorbidades.stream()
                .map(ComorbidadeMapper::toDTO)
                .toList();
    }

    public ComorbidadeResponseDTO criarComorbidade(ComorbidadeRequestDTO comorbidadeRequestDTO){
        if (comorbidadeRepository.existsByDescricaoIgnoreCase(comorbidadeRequestDTO.getDescricao())) {
            throw new IllegalArgumentException("Já existe uma comorbidade com essa descrição: " + comorbidadeRequestDTO.getDescricao()
            );
        }

        Comorbidade novaComorbidade = ComorbidadeMapper.toModel(comorbidadeRequestDTO);
        Comorbidade comorbidade = comorbidadeRepository.save(novaComorbidade);
        return ComorbidadeMapper.toDTO(comorbidade);
    }

    public void deletarComorbidade(int id){
        comorbidadeRepository.deleteById(id);
    }
}
