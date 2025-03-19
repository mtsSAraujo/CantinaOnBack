package gov.fatec.manumanager.controller.interfaces;

import gov.fatec.manumanager.dto.request.EquipamentoRequestDto;
import gov.fatec.manumanager.dto.response.EquipamentoResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/equipament")
public interface EquipamentoController {

    @GetMapping
    @Operation(description = "Endpoint responsável por encontrar equipamentos")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Equipamentos encontrados com sucesso"),
                    @ApiResponse(responseCode = "401", description = "Usuário não possui permissao para acessar o recurso"),
                    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
            }
    )
    ResponseEntity<List<EquipamentoResponseDto>> findAll();

    @GetMapping("/{id}")
    @Operation(description = "Endpoint responsável por encontrar equipamentos")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Equipamento encontrado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "ID digitado não é válido"),
                    @ApiResponse(responseCode = "401", description = "Usuário não possui permissao para acessar o recurso"),
                    @ApiResponse(responseCode = "404", description = "Equipamento não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
            }
    )
    ResponseEntity<EquipamentoResponseDto> findById(@PathVariable Long id);

    @PostMapping
    @Operation(description = "Endpoint responsável pela criação de um equipamento")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Equipamento criado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Body digitado não é válido"),
                    @ApiResponse(responseCode = "401", description = "Usuário não possui permissao para acessar o recurso"),
                    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
            }
    )
    ResponseEntity<EquipamentoResponseDto> createEquipament(@RequestBody EquipamentoRequestDto equipamentoRequestDto);

    @PutMapping("/{id}")
    @Operation(description = "Endpoint responsável pela atualização de um equipamento")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Equipamento atualizado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Body digitado não é válido"),
                    @ApiResponse(responseCode = "401", description = "Usuário não possui permissao para acessar o recurso"),
                    @ApiResponse(responseCode = "404", description = "Equipamento não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
            }
    )
    ResponseEntity<EquipamentoResponseDto> updateEquipament(@RequestParam long id, @RequestBody EquipamentoRequestDto equipamentoRequestDto);

    @DeleteMapping("{id}")
    @Operation(description = "Endpoint responsável pela exclusão de um equipamento")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "204", description = "Equipamento deletado com sucesso"),
                    @ApiResponse(responseCode = "401", description = "Usuário não possui permissao para acessar o recurso"),
                    @ApiResponse(responseCode = "404", description = "Equipamento não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
            }
    )
    ResponseEntity<Void> deleteEquipament(@RequestParam long id);
}
