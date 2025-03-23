package gov.fatec.manumanager.dto.response;

import gov.fatec.manumanager.utils.enumStatus.PrioridadeOS;
import gov.fatec.manumanager.utils.enumStatus.StatusOrdemDeServico;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record OrdemServicoResponseDto(long id, long equipamentoId, String descricaoProblema, LocalDateTime dataAbertura, LocalDateTime dataConclusao, StatusOrdemDeServico status, PrioridadeOS prioridadeOS) {
}