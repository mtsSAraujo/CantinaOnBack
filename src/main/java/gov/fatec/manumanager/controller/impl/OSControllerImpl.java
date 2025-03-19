package gov.fatec.manumanager.controller.impl;

import gov.fatec.manumanager.controller.interfaces.OSController;
import gov.fatec.manumanager.dto.request.OrdemServicoRequestDto;
import gov.fatec.manumanager.dto.response.OrdemServicoResponseDto;
import gov.fatec.manumanager.service.OrdemServicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OSControllerImpl implements OSController {

    private final OrdemServicoService ordemServicoService;

    @Override
    public ResponseEntity<OrdemServicoResponseDto> createOS(Long equipamentId, OrdemServicoRequestDto ordemServicoRequestDto) {
        return null;
    }
}
