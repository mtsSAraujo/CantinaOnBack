package gov.fatec.cantinaOn.dto.response;

import gov.fatec.cantinaOn.utils.TiposDeUsuario;
import gov.fatec.cantinaOn.utils.enumStatus.StatusUsuario;
import lombok.Builder;

@Builder
public record UsuarioResponseDto(Long id, String nome, String email, TiposDeUsuario tipoUsuario, StatusUsuario status) {
}
