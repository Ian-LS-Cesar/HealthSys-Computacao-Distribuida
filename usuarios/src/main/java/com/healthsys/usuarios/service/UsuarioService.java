package com.healthsys.usuarios.service;

import com.healthsys.usuarios.dto.UsuarioResponseDTO;
import com.healthsys.usuarios.mapper.UsuarioMapper;
import com.healthsys.usuarios.model.Usuario;
import com.healthsys.usuarios.repository.PerfilRepository;
import com.healthsys.usuarios.repository.UsuarioRepository;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Setter
@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<UsuarioResponseDTO> getUsuarios(){
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(UsuarioMapper::toDTO).toList();
    }
}
