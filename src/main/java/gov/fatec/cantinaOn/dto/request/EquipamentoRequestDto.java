package gov.fatec.cantinaOn.dto.request;

import gov.fatec.cantinaOn.utils.enumStatus.StatusEquipamento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record EquipamentoRequestDto(@NotNull String nome,
                                    @NotNull String tipo,
                                    @NotNull String localizacao,
                                    @NotNull StatusEquipamento status,
                                    @NotBlank LocalDateTime dataInstalacao,
                                    LocalDateTime ultimaManutencao) {
}
