package gov.fatec.manumanager.dto.converter;

import gov.fatec.manumanager.dto.request.UsuarioRequestDto;
import gov.fatec.manumanager.dto.response.UsuarioResponseDto;
import gov.fatec.manumanager.entity.Usuario;
import gov.fatec.manumanager.utils.enumStatus.StatusUsuario;

import java.util.Objects;

public class UsuarioConverter {

    public static UsuarioResponseDto fromEntity(Usuario usuario) {
        return UsuarioResponseDto.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .tipoUsuario(usuario.getTipoUsuario())
                .status(usuario.getStatus())
                .build();
    }

    public static Usuario fromDto(UsuarioRequestDto usuarioRequestDto) {
        return Usuario.builder()
                .email(usuarioRequestDto.email())
                .nome(usuarioRequestDto.nome())
                .senha(usuarioRequestDto.senha())
                .tipoUsuario(usuarioRequestDto.tipoUsuario())
                .status(usuarioRequestDto.status() == null || usuarioRequestDto.status().toString().isEmpty() ? StatusUsuario.ATIVO : usuarioRequestDto.status())
                .build();
    }
}
