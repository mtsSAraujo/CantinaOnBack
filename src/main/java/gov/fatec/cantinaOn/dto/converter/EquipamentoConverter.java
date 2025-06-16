package gov.fatec.cantinaOn.dto.converter;

import gov.fatec.cantinaOn.dto.request.EquipamentoRequestDto;
import gov.fatec.cantinaOn.dto.response.EquipamentoResponseDto;
import gov.fatec.cantinaOn.entity.Equipamento;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class EquipamentoConverter {

    public static EquipamentoResponseDto fromEntity(Equipamento equipamento) {
        return new EquipamentoResponseDto(
                equipamento.getId(),
                equipamento.getNome(),
                equipamento.getTipo(),
                equipamento.getLocalizacao(),
                equipamento.getStatus(),
                equipamento.getDataInstalacao(),
                equipamento.getUltimaManutencao(),
                equipamento.getOrdensServico().stream().map(OrdemServicoConverter::fromEntity).toList()
        );
    }

    public static Equipamento fromDto(EquipamentoRequestDto equipamentoRequestDto) {
        return Equipamento.builder()
                .nome(equipamentoRequestDto.nome())
                .tipo(equipamentoRequestDto.tipo())
                .localizacao(equipamentoRequestDto.localizacao())
                .status(equipamentoRequestDto.status())
                .dataInstalacao(equipamentoRequestDto.dataInstalacao())
                .ultimaManutencao(equipamentoRequestDto.ultimaManutencao() != null ? equipamentoRequestDto.ultimaManutencao() : LocalDateTime.now())
                .ordensServico(new ArrayList<>())
                .build();
    }
}
