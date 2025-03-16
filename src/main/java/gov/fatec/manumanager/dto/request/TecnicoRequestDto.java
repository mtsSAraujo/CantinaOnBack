package gov.fatec.manumanager.dto.request;

import gov.fatec.manumanager.entity.Atribuicao;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record TecnicoRequestDto(@NotBlank String especialidade,@NotBlank String disponibilidade, List<Atribuicao> atribuicoes) {
}
