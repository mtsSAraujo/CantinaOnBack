package gov.fatec.manumanager.dto.converter;

import gov.fatec.manumanager.dto.response.TecnicoResponseDto;
import gov.fatec.manumanager.entity.Tecnico;

public class TecnicoConverter {

    public static TecnicoResponseDto fromEntity(Tecnico tecnico) {
        return new TecnicoResponseDto(tecnico.getId(),
                UsuarioConverter.fromEntity(tecnico.getUsuario()),
                tecnico.getEspecializacao(),
                tecnico.getDisponibilidade(),
                tecnico.getAtribuicoes()
        );
    }
}
