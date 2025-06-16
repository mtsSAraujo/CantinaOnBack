package gov.fatec.cantinaOn.dto.converter;

import gov.fatec.cantinaOn.dto.request.UsuarioRequestDto;
import gov.fatec.cantinaOn.dto.response.UsuarioResponseDto;
import gov.fatec.cantinaOn.entity.Usuario;
import gov.fatec.cantinaOn.utils.enumStatus.StatusUsuario;

import static gov.fatec.cantinaOn.utils.enumStatus.StatusUsuario.DEFAULT;

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
                .status(usuarioRequestDto.status() == DEFAULT ? StatusUsuario.ATIVO : usuarioRequestDto.status())
                .build();
    }
}
