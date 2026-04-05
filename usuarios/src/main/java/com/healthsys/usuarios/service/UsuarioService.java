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
    private final PerfilRepository perfilRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, PerfilRepository perfilRepository) {
        this.usuarioRepository = usuarioRepository;
        this.perfilRepository = perfilRepository;
    }

    public List<UsuarioResponseDTO> getUsuarios(){
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(UsuarioMapper::toDTO).toList();
    }
}
