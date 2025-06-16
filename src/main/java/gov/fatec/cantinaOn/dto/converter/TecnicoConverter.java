package gov.fatec.cantinaOn.dto.converter;

import gov.fatec.cantinaOn.dto.response.TecnicoResponseDto;
import gov.fatec.cantinaOn.entity.Tecnico;

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
