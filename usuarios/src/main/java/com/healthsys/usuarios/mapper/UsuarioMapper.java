package com.healthsys.usuarios.mapper;

import com.healthsys.usuarios.dto.UsuarioRequestDTO;
import com.healthsys.usuarios.dto.UsuarioResponseDTO;
import com.healthsys.usuarios.model.Perfil;
import com.healthsys.usuarios.model.Usuario;

import java.time.LocalDate;

public class UsuarioMapper {

    public static UsuarioResponseDTO toDTO(Usuario usuario) {
        UsuarioResponseDTO usuarioDTO = new UsuarioResponseDTO();
        usuarioDTO.setId(usuario.getId().toString());
        usuarioDTO.setNome(usuario.getNome());
        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setDataNascimento(usuario.getDataNascimento().toString());

        // Se quiser manter, deixa. Em produção o ideal é NÃO expor senha no response.
        usuarioDTO.setSenha(usuario.getSenha());

        if (usuario.getPerfil() != null) {
            usuarioDTO.setPerfil(usuario.getPerfil().getDescricao());
        }

        return usuarioDTO;
    }

    public static Usuario toModel(UsuarioRequestDTO usuarioDTO, Perfil perfil) {
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioDTO.getNome());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setSenha(usuarioDTO.getSenha());
        usuario.setDataNascimento(LocalDate.parse(usuarioDTO.getDataNascimento()));
        usuario.setPerfil(perfil);
        return usuario;
    }
}
