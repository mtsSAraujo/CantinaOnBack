package gov.fatec.manumanager.dto.request;

import jakarta.validation.constraints.NotNull;

public record UserInfo(@NotNull String email, @NotNull String password) {
}
