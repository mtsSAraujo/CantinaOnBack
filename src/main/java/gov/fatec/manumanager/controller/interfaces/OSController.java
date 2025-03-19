package gov.fatec.manumanager.controller.interfaces;

import gov.fatec.manumanager.dto.request.OrdemServicoRequestDto;
import gov.fatec.manumanager.dto.response.OrdemServicoResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/os")
public interface OSController {

    @PostMapping("/{equipamentId}")
    @Operation(description = "Endpoint responsável pela criação de uma nova Ordem de serviço")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "OS criada com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Campo(s) de requisição inválido(s)"),
                    @ApiResponse(responseCode = "401", description = "Usuário não possui permissao para acessar o recurso"),
                    @ApiResponse(responseCode = "404", description = "Equipamento não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
            }
    )
    ResponseEntity<OrdemServicoResponseDto> createOS(@PathVariable Long equipamentId, @RequestBody OrdemServicoRequestDto ordemServicoRequestDto);

}
