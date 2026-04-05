package com.healthsys.usuarios.mapper;

import com.healthsys.usuarios.dto.UsuarioResponseDTO;
import com.healthsys.usuarios.model.Usuario;

public class UsuarioMapper {
    public static UsuarioResponseDTO toDTO(Usuario usuario){
        UsuarioResponseDTO usuarioDTO = new UsuarioResponseDTO();
        usuarioDTO.setId(usuario.getId().toString());
        usuarioDTO.setNome(usuario.getNome());
        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setSenha(usuario.getSenha());
        usuarioDTO.setDataNascimento(usuario.getDataNascimento().toString());

        if (usuario.getDataNascimento()!=null){
            usuarioDTO.setPerfil(usuario.getPerfil().getDescricao());
        }

        return usuarioDTO;
    }
}
