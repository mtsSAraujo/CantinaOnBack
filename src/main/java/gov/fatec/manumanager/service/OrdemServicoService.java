package gov.fatec.manumanager.service;

import gov.fatec.manumanager.dto.converter.OrdemServicoConverter;
import gov.fatec.manumanager.dto.request.OrdemServicoRequestDto;
import gov.fatec.manumanager.dto.response.OrdemServicoResponseDto;
import gov.fatec.manumanager.entity.Equipamento;
import gov.fatec.manumanager.entity.OrdemServico;
import gov.fatec.manumanager.exception.models.EquipamentNotFoundException;
import gov.fatec.manumanager.exception.models.InactiveEquipamentException;
import gov.fatec.manumanager.exception.models.OSAlreadyOpenedException;
import gov.fatec.manumanager.exception.models.OSNotFoundException;
import gov.fatec.manumanager.repository.EquipamentoRepository;
import gov.fatec.manumanager.repository.OrdemServicoRepository;
import gov.fatec.manumanager.utils.enumStatus.StatusEquipamento;
import gov.fatec.manumanager.utils.enumStatus.StatusOrdemDeServico;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdemServicoService {

    private final OrdemServicoRepository ordemServicoRepository;
    private final EquipamentoRepository equipamentoRepository;

    @Transactional
    public OrdemServicoResponseDto createOS(Long equipamentId, OrdemServicoRequestDto ordemServicoRequestDto) {
        Equipamento equipamento = equipamentoRepository.findById(equipamentId).orElseThrow(
                () -> new EquipamentNotFoundException("ID: " + equipamentId + " não corresponde a nenhum equipamento")
        );

        if(equipamento.getStatus() == StatusEquipamento.DESATIVADO) {
            throw new InactiveEquipamentException("Equipamento encontra-se desativado no momento");
        }

        checkIfStatusOSIsOpen(equipamento);

        OrdemServico ordemServico = OrdemServicoConverter.fromDto(ordemServicoRequestDto, equipamento);
        ordemServicoRepository.save(ordemServico);

        equipamento.getOrdensServico().add(ordemServico);
        equipamentoRepository.save(equipamento);

        return OrdemServicoConverter.fromEntity(ordemServico);
    }

    private void checkIfStatusOSIsOpen(Equipamento equipamento) {
        List<OrdemServico> ordensServico = equipamento.getOrdensServico();

        if(ordensServico.isEmpty()) {
            return;
        }

        ordensServico.sort(Comparator.comparing(OrdemServico::getDataAbertura));
        StatusOrdemDeServico lastOrderStatus = ordensServico.get(0).getStatus();

        if(lastOrderStatus.equals(StatusOrdemDeServico.ABERTA) || lastOrderStatus.equals(StatusOrdemDeServico.EM_ANDAMENTO)) {
            throw new OSAlreadyOpenedException("Ordem de serviço ja aberta para o equipamento: " + equipamento.getId() + " --- Status OS: " + lastOrderStatus);
        }
    }

    @Transactional
    public OrdemServicoResponseDto updateOS(Long ordemServicoId) {

        return null;
    }

    public List<OrdemServicoResponseDto> findAll(Long equipamentoId) {
        List<OrdemServico> ordensServico = equipamentoRepository.findById(equipamentoId)
                .orElseThrow(() -> new EquipamentNotFoundException("Equipamento não encontrado para o ID: " + equipamentoId))
                .getOrdensServico();

        return ordensServico.stream().map(OrdemServicoConverter::fromEntity).toList();
    }

    public OrdemServicoResponseDto findById(Long equipamentoId, Long ordemServicoId) {
        OrdemServico ordemServico = equipamentoRepository.findById(equipamentoId)
                .orElseThrow(() -> new EquipamentNotFoundException("Equipamento não encontrado para o ID: " + equipamentoId))
                .getOrdensServico().stream()
                .filter(os -> os.getId().equals(ordemServicoId))
                .findFirst()
                .orElseThrow(() -> new OSNotFoundException("Ordem de serviço não encontrada para o ID: " + ordemServicoId));

        return OrdemServicoConverter.fromEntity(ordemServico);
    }

}
