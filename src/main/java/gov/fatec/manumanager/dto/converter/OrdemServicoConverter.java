package gov.fatec.manumanager.dto.converter;

import gov.fatec.manumanager.dto.request.OrdemServicoRequestDto;
import gov.fatec.manumanager.dto.response.OrdemServicoResponseDto;
import gov.fatec.manumanager.entity.Equipamento;
import gov.fatec.manumanager.entity.OrdemServico;
import gov.fatec.manumanager.utils.enumStatus.StatusOrdemDeServico;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrdemServicoConverter {

    public static OrdemServico fromDto(OrdemServicoRequestDto ordemServicoRequestDto, Equipamento equipamento) {
        return OrdemServico.builder()
                .status(StatusOrdemDeServico.ABERTA)
                .descricaoProblema(ordemServicoRequestDto.descricaoProblema())
                .equipamento(equipamento)
                .atribuicoes(new ArrayList<>())
                .prioridade(ordemServicoRequestDto.prioridade())
                .dataAbertura(LocalDateTime.now())
                .dataConclusao(null)
                .build();
    }

    public static OrdemServicoResponseDto fromEntity(OrdemServico ordemServico) {
        return OrdemServicoResponseDto.builder()
                .id(ordemServico.getId())
                .equipamentoId(ordemServico.getEquipamento().getId())
                .descricaoProblema(ordemServico.getDescricaoProblema())
                .dataAbertura(ordemServico.getDataAbertura())
                .dataConclusao(ordemServico.getDataConclusao())
                .status(ordemServico.getStatus())
                .prioridadeOS(ordemServico.getPrioridade())
                .build();
    }
}
