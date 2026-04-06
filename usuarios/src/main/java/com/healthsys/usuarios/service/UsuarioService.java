package com.healthsys.usuarios.service;

import com.healthsys.usuarios.dto.UsuarioRequestDTO;
import com.healthsys.usuarios.dto.UsuarioResponseDTO;
import com.healthsys.usuarios.mapper.UsuarioMapper;
import com.healthsys.usuarios.model.Perfil;
import com.healthsys.usuarios.model.Usuario;
import com.healthsys.usuarios.repository.PerfilRepository;
import com.healthsys.usuarios.repository.UsuarioRepository;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Setter
@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PerfilRepository perfilRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PerfilRepository perfilRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.perfilRepository = perfilRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UsuarioResponseDTO> getUsuarios(){
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(UsuarioMapper::toDTO).toList();
    }

    public UsuarioResponseDTO criarUsuario(UsuarioRequestDTO usuarioRequestDTO){
        Perfil perfil = perfilRepository.findById(usuarioRequestDTO.getPerfil())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Perfil não encontrado com o ID: " + usuarioRequestDTO.getPerfil()));

        Usuario novoUsuario = UsuarioMapper.toModel(usuarioRequestDTO, perfil);
        novoUsuario.setSenha(passwordEncoder.encode(usuarioRequestDTO.getSenha()));

        Usuario usuarioSalvo = usuarioRepository.save(novoUsuario);
        return UsuarioMapper.toDTO(usuarioSalvo);
    }

}
