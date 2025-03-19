package gov.fatec.manumanager.dto.request;

import gov.fatec.manumanager.entity.OrdemServico;
import gov.fatec.manumanager.utils.enumStatus.StatusEquipamento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record EquipamentoRequestDto(@NotNull String nome,
                                    @NotNull String tipo,
                                    @NotNull String localizacao,
                                    @NotNull StatusEquipamento status,
                                    @NotBlank LocalDateTime dataInstalacao,
                                    LocalDateTime ultimaManutencao,
                                    List<OrdemServico> ordensServico) {
}
