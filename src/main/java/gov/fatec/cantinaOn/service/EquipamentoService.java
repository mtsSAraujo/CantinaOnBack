package gov.fatec.cantinaOn.service;

import gov.fatec.cantinaOn.dto.converter.EquipamentoConverter;
import gov.fatec.cantinaOn.dto.request.EquipamentoRequestDto;
import gov.fatec.cantinaOn.dto.response.EquipamentoResponseDto;
import gov.fatec.cantinaOn.entity.Equipamento;
import gov.fatec.cantinaOn.exception.models.EquipamentNotFoundException;
import gov.fatec.cantinaOn.exception.models.InactiveEquipamentException;
import gov.fatec.cantinaOn.repository.EquipamentoRepository;
import gov.fatec.cantinaOn.utils.enumStatus.StatusEquipamento;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EquipamentoService {

    private final EquipamentoRepository equipamentoRepository;

    public List<EquipamentoResponseDto> findAll() {
        List<Equipamento> equipamentos = equipamentoRepository.findAll();
        return equipamentos.stream().map(EquipamentoConverter::fromEntity).collect(Collectors.toList());
    }

    public EquipamentoResponseDto findById(Long id) {
        Equipamento equipamento = equipamentoRepository.findById(id).orElseThrow(
                () -> new EquipamentNotFoundException("ID: " + id + " não corresponde a nenhum equipamento")
        );

        return EquipamentoConverter.fromEntity(equipamento);
    }

    @Transactional
    public EquipamentoResponseDto createEquipament(EquipamentoRequestDto equipamentoRequestDto) {
        Equipamento equipamentoCriado = EquipamentoConverter.fromDto(equipamentoRequestDto);
        return EquipamentoConverter.fromEntity(equipamentoRepository.save(equipamentoCriado));
    }

    @Transactional
    public EquipamentoResponseDto updateEquipament(Long id, EquipamentoRequestDto equipamentoRequestDto) {
        equipamentoRepository.findById(id).orElseThrow(
                () -> new EquipamentNotFoundException("ID: " + id + " não corresponde a nenhum equipamento")
        );

        Equipamento equipamentoCriado = EquipamentoConverter.fromDto(equipamentoRequestDto);
        equipamentoCriado.setId(id);

        return EquipamentoConverter.fromEntity(equipamentoRepository.save(equipamentoCriado));
    }

    @Transactional
    public void deleteEquipament(Long id) {
        Equipamento equipamento = equipamentoRepository.findById(id).orElseThrow(
                () -> new EquipamentNotFoundException("ID: " + id + " não corresponde a nenhum equipamento")
        );

        if(equipamento.getStatus() == StatusEquipamento.DESATIVADO) {
            throw new InactiveEquipamentException("Equipamento já está desativado");
        } else {
            equipamento.setStatus(StatusEquipamento.DESATIVADO);
            equipamentoRepository.save(equipamento);
        }

    }
}
