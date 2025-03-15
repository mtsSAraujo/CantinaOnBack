package gov.fatec.manumanager.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UnauthorizedAcessException extends RuntimeException {
    public UnauthorizedAcessException(String message) {
        super(message);
    }
}
