package gov.fatec.manumanager.dto;

import gov.fatec.manumanager.utils.TiposDeUsuario;
import gov.fatec.manumanager.utils.enumStatus.StatusUsuario;

public record UsuarioRequestDto(String nome, String email, String senha, TiposDeUsuario tipoUsuario, StatusUsuario status) {
}
