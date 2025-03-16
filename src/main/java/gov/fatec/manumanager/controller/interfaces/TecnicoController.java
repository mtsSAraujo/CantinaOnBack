package gov.fatec.manumanager.controller.interfaces;

import gov.fatec.manumanager.dto.request.TecnicoRequestDto;
import gov.fatec.manumanager.dto.response.TecnicoResponseDto;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/tech")
public interface TecnicoController {

    @PutMapping("/{id}")
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
