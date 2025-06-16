package gov.fatec.cantinaOn.dto.request;

import gov.fatec.cantinaOn.entity.Atribuicao;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record TecnicoRequestDto(@NotBlank String especialidade,@NotBlank String disponibilidade, List<Atribuicao> atribuicoes) {
}
