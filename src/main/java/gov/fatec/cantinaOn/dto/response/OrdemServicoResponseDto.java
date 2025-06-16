package gov.fatec.cantinaOn.dto.response;

import gov.fatec.cantinaOn.utils.enumStatus.PrioridadeOS;
import gov.fatec.cantinaOn.utils.enumStatus.StatusOrdemDeServico;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record OrdemServicoResponseDto(long id, long equipamentoId, String descricaoProblema, LocalDateTime dataAbertura, LocalDateTime dataConclusao, StatusOrdemDeServico status, PrioridadeOS prioridadeOS) {
}