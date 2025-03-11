package gov.fatec.manumanager.dto;

import gov.fatec.manumanager.utils.TiposDeUsuario;
import gov.fatec.manumanager.utils.enumStatus.StatusUsuario;

public record UsuarioResponseDto(Long id, String nome, String email, TiposDeUsuario tipoUsuario, StatusUsuario status) {
}
