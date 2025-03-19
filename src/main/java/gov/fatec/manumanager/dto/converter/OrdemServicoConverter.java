package gov.fatec.manumanager.dto.converter;

import gov.fatec.manumanager.dto.request.OrdemServicoRequestDto;
import gov.fatec.manumanager.dto.response.OrdemServicoResponseDto;
import gov.fatec.manumanager.entity.OrdemServico;

public class OrdemServicoConverter {

    public static OrdemServico fromDto(OrdemServicoRequestDto ordemServicoRequestDto) {
        return OrdemServico.builder()

                .build();
    }

    public static OrdemServicoResponseDto fromEntity(OrdemServico ordemServico) {
        return new OrdemServicoResponseDto();
    }
}
