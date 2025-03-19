package gov.fatec.manumanager.controller.interfaces;

import gov.fatec.manumanager.dto.request.TecnicoRequestDto;
import gov.fatec.manumanager.dto.request.UsuarioRequestDto;
import gov.fatec.manumanager.dto.response.TecnicoResponseDto;
import gov.fatec.manumanager.dto.response.UsuarioResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public interface UsuarioController {

    @GetMapping("/{id}")
    @Operation(description = "Endpoint responsável por encontrar usuário pelo seu ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "ID inválido"),
                    @ApiResponse(responseCode = "401", description = "Usuário não possui permissao para acessar o recurso"),
                    @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
            }
    )
    ResponseEntity<UsuarioResponseDto> findById(@PathVariable Long id);

    @GetMapping
    @Operation(description = "Endpoint responsável por encontrar todos os usuários")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Usuários encontrados com sucesso"),
                    @ApiResponse(responseCode = "401", description = "Usuário não possui permissao para acessar o recurso"),
                    @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
            }
    )
    ResponseEntity<List<UsuarioResponseDto>> findAll();

    @PostMapping("/register")
    @Operation(description = "Endpoint responsável pela criação de um novo usuário")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Campo(s) de requisição inválido(s)"),
                    @ApiResponse(responseCode = "401", description = "Usuário não possui permissao para acessar o recurso"),
                    @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
            }
    )
    ResponseEntity<UsuarioResponseDto> createUser(
            @RequestBody @Valid UsuarioRequestDto usuarioRequestDto
    );

    @DeleteMapping("/{id}")
    @Operation(description = "Endpoint responsável por deletar um usuário")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "ID inválido"),
                    @ApiResponse(responseCode = "401", description = "Usuário não possui permissao para acessar o recurso"),
                    @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
            }
    )
    ResponseEntity<Void> deleteUser(
            @PathVariable Long id
    );

    @PutMapping("/{id}")
    @Operation(description = "Endpoint responsável por atualizar um usuário")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "ID ou formato de requisição inválido(s)"),
                    @ApiResponse(responseCode = "401", description = "Usuário não possui permissão para acessar o recurso"),
                    @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
            }
    )
    ResponseEntity<UsuarioResponseDto> updateUser(
            @RequestBody @Valid UsuarioRequestDto usuarioRequestDto,
            @PathVariable Long id
    );

    @PutMapping("/{id}/tech")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Endpoint para atualizar um tecnico")
            }
    )
    ResponseEntity<TecnicoResponseDto> updateTechnician(
            @PathVariable Long id,
            @RequestBody TecnicoRequestDto tecnicoRequestDto
    );
}
