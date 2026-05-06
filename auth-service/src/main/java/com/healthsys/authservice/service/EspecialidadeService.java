package com.healthsys.authservice.service;

import com.healthsys.authservice.dto.EspecialidadeRequestDTO;
import com.healthsys.authservice.dto.EspecialidadeResponseDTO;
import com.healthsys.authservice.mapper.EspecialidadeMapper;
import com.healthsys.authservice.model.Especialidade;
import com.healthsys.authservice.repository.EspecialidadeRepository;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Getter
@Service
public class EspecialidadeService {
    private EspecialidadeRepository especialidadeRepository;

    public EspecialidadeService(EspecialidadeRepository especialidadeRepository) {
        this.especialidadeRepository = especialidadeRepository;
    }

    public List<EspecialidadeResponseDTO> getEspecialidades(){
        List<Especialidade> especialidades = especialidadeRepository.findAll();
        return especialidades.stream()
                .map(EspecialidadeMapper::toDTO)
                .toList();
    }

    public EspecialidadeResponseDTO criarEspecialidade(EspecialidadeRequestDTO especialidadeRequestDTO){
        if (especialidadeRepository.existsByDescricaoIgnoreCase(especialidadeRequestDTO.getDescricao())){
            throw new RuntimeException(
                    "Já existe uma especialidade com essa descrição: "+ especialidadeRequestDTO.getDescricao()
            );
        }
        Especialidade novaEspecialidade = EspecialidadeMapper.toModel(especialidadeRequestDTO);
        Especialidade especialidade = especialidadeRepository.save(novaEspecialidade);
        return EspecialidadeMapper.toDTO(especialidade);
    }

    public void deletarEspecialidade(int id){
        especialidadeRepository.deleteById(id);
    }
}
