package gov.fatec.manumanager.exception.controller;

import gov.fatec.manumanager.exception.ExceptionResponseBody;
import gov.fatec.manumanager.exception.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ExceptionResponseBody> handlePageNotFoundException(UserNotFoundException exc) {
        ExceptionResponseBody error = new ExceptionResponseBody();

        error.setTimeStamp(Instant.now());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.addError(exc.getMessage());
        LOGGER.error(exc.getMessage(), exc);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(HttpClientErrorException.Forbidden.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<ExceptionResponseBody> handleGenericException(HttpClientErrorException.Forbidden exc) {
        ExceptionResponseBody error = new ExceptionResponseBody();
        error.setTimeStamp(Instant.now());
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.addError("Acesso proibido ao recurso: " + exc.getMessage());

        LOGGER.error("Acesso proibido ao recurso", exc);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }


}
