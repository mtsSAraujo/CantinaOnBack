package gov.fatec.cantinaOn.exception.models;

public class OSAlreadyOpenedException extends RuntimeException {
    public OSAlreadyOpenedException(String message) {
        super(message);
    }
}
