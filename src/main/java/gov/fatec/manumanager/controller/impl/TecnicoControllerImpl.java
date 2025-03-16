package gov.fatec.manumanager.controller.impl;

import gov.fatec.manumanager.controller.interfaces.TecnicoController;
import gov.fatec.manumanager.dto.request.TecnicoRequestDto;
import gov.fatec.manumanager.dto.response.TecnicoResponseDto;
import gov.fatec.manumanager.service.TecnicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TecnicoControllerImpl implements TecnicoController {

    private final TecnicoService tecnicoService;

    @Override
    public ResponseEntity<TecnicoResponseDto> updateTechnician(Long id, TecnicoRequestDto tecnicoRequestDto) {
        return new ResponseEntity<>(tecnicoService.updateTechnician(id, tecnicoRequestDto), HttpStatus.OK);
    }
}
