package gov.fatec.manumanager.controller;

import gov.fatec.manumanager.dto.UsuarioRequestDto;
import gov.fatec.manumanager.dto.UsuarioResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public interface UsuarioController {

    @GetMapping("/{id}")
    @Operation(description = "Endpoint responsável por encontrar usuário pelo seu ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso", content = {})
            }
    )
    ResponseEntity<UsuarioResponseDto> findById(@PathVariable(required = true) Long id);

    @GetMapping
    @Operation(description = "Endpoint responsável por encontrar todos os usuários")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200")
            }
    )
    ResponseEntity<List<UsuarioResponseDto>> findAll();

    @PostMapping
    @Operation(description = "Endpoint responsável pela criação de um novo usuário")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Usuário criado com sucesso", content = {})
            }
    )
    ResponseEntity<UsuarioResponseDto> createUser(
            @RequestBody UsuarioRequestDto usuarioRequestDto
    );
}
