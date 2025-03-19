package gov.fatec.manumanager.controller.impl;

import gov.fatec.manumanager.controller.interfaces.UsuarioController;
import gov.fatec.manumanager.dto.request.TecnicoRequestDto;
import gov.fatec.manumanager.dto.request.UsuarioRequestDto;
import gov.fatec.manumanager.dto.response.TecnicoResponseDto;
import gov.fatec.manumanager.dto.response.UsuarioResponseDto;
import gov.fatec.manumanager.service.TecnicoService;
import gov.fatec.manumanager.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UsuarioControllerImpl implements UsuarioController {

    private final UsuarioService usuarioService;

    private final TecnicoService tecnicoService;

    @Override
    public ResponseEntity<UsuarioResponseDto> findById(Long id) {
        return new ResponseEntity<>(usuarioService.findById(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<UsuarioResponseDto>> findAll() {
        return new ResponseEntity<>(usuarioService.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UsuarioResponseDto> createUser(UsuarioRequestDto usuarioRequestDto) {
        return new ResponseEntity<>(usuarioService.createUser(usuarioRequestDto), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteUser(Long id) {
        usuarioService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<UsuarioResponseDto> updateUser(UsuarioRequestDto usuarioRequestDto, Long id) {
        return new ResponseEntity<>(usuarioService.updateUser(id, usuarioRequestDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TecnicoResponseDto> updateTechnician(Long id, TecnicoRequestDto tecnicoRequestDto) {
        return new ResponseEntity<>(tecnicoService.updateTechnician(id, tecnicoRequestDto), HttpStatus.OK);
    }
}
