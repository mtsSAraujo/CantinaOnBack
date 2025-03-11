package gov.fatec.manumanager.controller.impl;

import gov.fatec.manumanager.controller.UsuarioController;
import gov.fatec.manumanager.dto.UsuarioResponseDto;
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

    @Override
    public ResponseEntity<UsuarioResponseDto> findById(Long id) {
        return new ResponseEntity<>(usuarioService.findById(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<UsuarioResponseDto>> findAll() {
        return new ResponseEntity<>(usuarioService.findAll(), HttpStatus.OK);
    }
}
