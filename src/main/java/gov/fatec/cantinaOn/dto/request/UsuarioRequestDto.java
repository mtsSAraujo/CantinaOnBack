package gov.fatec.cantinaOn.dto.request;

import gov.fatec.cantinaOn.utils.TiposDeUsuario;
import gov.fatec.cantinaOn.utils.enumStatus.StatusUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UsuarioRequestDto(
        @NotBlank(message = "Nome é obrigatório") String nome,

        @NotBlank(message = "Email é obrigatório") @Email(message = "Email inválido") String email,

        @NotBlank(message = "Senha é obrigatória") @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres") String senha,

        @NotNull(message = "Tipo de usuário é obrigatório") TiposDeUsuario tipoUsuario,

        StatusUsuario status
        ){}