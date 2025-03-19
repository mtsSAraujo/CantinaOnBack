package gov.fatec.manumanager.dto.response;

import gov.fatec.manumanager.entity.OrdemServico;
import gov.fatec.manumanager.utils.enumStatus.StatusEquipamento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record EquipamentoResponseDto(@NotNull long id,
                                     @NotNull String nome,
                                     @NotNull String tipo,
                                     @NotNull String localizacao,
                                     @NotNull StatusEquipamento status,
                                     @NotBlank LocalDateTime dataInstalacao,
                                     LocalDateTime ultimaManutencao,
                                     List<OrdemServico> ordensServico) {
}
