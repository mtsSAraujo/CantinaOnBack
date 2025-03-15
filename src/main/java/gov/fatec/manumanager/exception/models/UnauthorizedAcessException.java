package gov.fatec.manumanager.exception.models;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UnauthorizedAcessException extends RuntimeException {
    public UnauthorizedAcessException(String message) {
        super(message);
    }
}
