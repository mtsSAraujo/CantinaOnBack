package gov.fatec.manumanager.controller.impl;

import gov.fatec.manumanager.controller.interfaces.OSController;
import gov.fatec.manumanager.dto.request.OrdemServicoRequestDto;
import gov.fatec.manumanager.dto.response.OrdemServicoResponseDto;
import gov.fatec.manumanager.service.OrdemServicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OSControllerImpl implements OSController {

    private final OrdemServicoService ordemServicoService;

    @Override
    public ResponseEntity<OrdemServicoResponseDto> createOS(Long equipamentId, OrdemServicoRequestDto ordemServicoRequestDto) {
        OrdemServicoResponseDto ordemServico = ordemServicoService.createOS(equipamentId, ordemServicoRequestDto);
        return new ResponseEntity<>(ordemServico, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<OrdemServicoResponseDto>> findAllOS(Long equipamentId) {
        return new ResponseEntity<>(ordemServicoService.findAll(equipamentId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<OrdemServicoResponseDto> findOSDetails(Long equipamentId, Long osId) {
        return new ResponseEntity<>(ordemServicoService.findById(equipamentId, osId), HttpStatus.OK);
    }
}
