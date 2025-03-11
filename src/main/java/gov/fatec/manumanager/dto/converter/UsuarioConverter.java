package gov.fatec.manumanager.dto.converter;

import gov.fatec.manumanager.dto.UsuarioResponseDto;
import gov.fatec.manumanager.entity.Usuario;

public class UsuarioConverter {

    public static UsuarioResponseDto fromEntity(Usuario usuario) {
        return new UsuarioResponseDto(usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getTipoUsuario(),
                usuario.getStatus()
        );
    }
}
