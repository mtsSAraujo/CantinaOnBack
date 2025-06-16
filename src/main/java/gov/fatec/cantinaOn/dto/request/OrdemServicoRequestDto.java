package gov.fatec.cantinaOn.dto.request;

import gov.fatec.cantinaOn.utils.enumStatus.PrioridadeOS;
import jakarta.validation.constraints.NotBlank;

public record OrdemServicoRequestDto(@NotBlank String descricaoProblema,@NotBlank PrioridadeOS prioridade) {
}
