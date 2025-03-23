package gov.fatec.manumanager.exception.models;

public class OSAlreadyOpenedException extends RuntimeException {
    public OSAlreadyOpenedException(String message) {
        super(message);
    }
}
