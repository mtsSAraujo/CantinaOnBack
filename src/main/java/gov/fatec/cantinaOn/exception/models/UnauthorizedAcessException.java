package gov.fatec.cantinaOn.exception.models;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UnauthorizedAcessException extends RuntimeException {
    public UnauthorizedAcessException(String message) {
        super(message);
    }
}
