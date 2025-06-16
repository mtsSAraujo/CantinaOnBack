package gov.fatec.cantinaOn.dto.response;

import gov.fatec.cantinaOn.entity.Atribuicao;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record TecnicoResponseDto(
        Long id,
        UsuarioResponseDto usuarioResponseDto,
        @NotBlank String especializacao,
        @NotBlank String disponibilidade,
        List<Atribuicao> atribuicoes
        ) {
}
