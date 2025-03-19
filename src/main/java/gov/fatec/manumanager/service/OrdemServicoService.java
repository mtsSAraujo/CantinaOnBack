package gov.fatec.manumanager.service;

import gov.fatec.manumanager.dto.request.OrdemServicoRequestDto;
import gov.fatec.manumanager.dto.response.OrdemServicoResponseDto;
import gov.fatec.manumanager.entity.Equipamento;
import gov.fatec.manumanager.exception.models.EquipamentNotFoundException;
import gov.fatec.manumanager.repository.EquipamentoRepository;
import gov.fatec.manumanager.repository.OrdemServicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrdemServicoService {

    private final OrdemServicoRepository ordemServicoRepository;
    private final EquipamentoRepository equipamentoRepository;

    public OrdemServicoResponseDto createOS(Long equipamentId, OrdemServicoRequestDto ordemServicoRequestDto) {
        Equipamento equipamento = equipamentoRepository.findById(equipamentId).orElseThrow(
                () -> new EquipamentNotFoundException("ID: " + equipamentId + " não corresponde a nenhum equipamento")
        );

        equipamento.getOrdensServico().add(null);
        equipamentoRepository.save(equipamento);

        return null;
    }
}
