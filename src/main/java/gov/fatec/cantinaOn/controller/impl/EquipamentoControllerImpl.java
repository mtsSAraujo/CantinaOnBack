package gov.fatec.cantinaOn.controller.impl;

import gov.fatec.cantinaOn.controller.interfaces.EquipamentoController;
import gov.fatec.cantinaOn.dto.request.EquipamentoRequestDto;
import gov.fatec.cantinaOn.dto.response.EquipamentoResponseDto;
import gov.fatec.cantinaOn.service.EquipamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EquipamentoControllerImpl implements EquipamentoController {

    private final EquipamentoService equipamentoService;

    @Override
    public ResponseEntity<List<EquipamentoResponseDto>> findAll() {
        return new ResponseEntity<>(equipamentoService.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<EquipamentoResponseDto> findById(Long id) {
        return new ResponseEntity<>(equipamentoService.findById(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<EquipamentoResponseDto> createEquipament(EquipamentoRequestDto equipamentoRequestDto) {
        return new ResponseEntity<>(equipamentoService.createEquipament(equipamentoRequestDto), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<EquipamentoResponseDto> updateEquipament(long id, EquipamentoRequestDto equipamentoRequestDto) {
        return new ResponseEntity<>(equipamentoService.updateEquipament(id, equipamentoRequestDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteEquipament(long id) {
        equipamentoService.deleteEquipament(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
