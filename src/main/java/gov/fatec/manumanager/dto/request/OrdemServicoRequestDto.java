package gov.fatec.manumanager.dto.request;

import gov.fatec.manumanager.utils.enumStatus.PrioridadeOS;
import jakarta.validation.constraints.NotBlank;

public record OrdemServicoRequestDto(@NotBlank String descricaoProblema,@NotBlank PrioridadeOS prioridade) {
}
