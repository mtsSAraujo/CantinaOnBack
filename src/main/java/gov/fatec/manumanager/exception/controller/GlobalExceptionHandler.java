package gov.fatec.manumanager.exception.controller;

import gov.fatec.manumanager.exception.ExceptionResponseBody;
import gov.fatec.manumanager.exception.models.EquipamentNotFoundException;
import gov.fatec.manumanager.exception.models.InactiveEquipamentException;
import gov.fatec.manumanager.exception.models.InactiveUserException;
import gov.fatec.manumanager.exception.models.OSAlreadyOpenedException;
import gov.fatec.manumanager.exception.models.OSNotFoundException;
import gov.fatec.manumanager.exception.models.UnauthorizedAcessException;
import gov.fatec.manumanager.exception.models.UserAlreadyExistsException;
import gov.fatec.manumanager.exception.models.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpClientErrorException.Forbidden;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(InternalServerError.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ExceptionResponseBody> handleGenericException(InternalServerError exc) {
        ExceptionResponseBody error = new ExceptionResponseBody();

        error.setTimeStamp(ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT));
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.addError(exc.getMessage());

        log.error(exc.getMessage(), exc);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }


    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ExceptionResponseBody> handleUserNotFoundException(UserNotFoundException exc) {
        ExceptionResponseBody error = new ExceptionResponseBody();

        error.setPath("/api/user/{id}");
        error.setTimeStamp(ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT));
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.addError(exc.getMessage());

        log.error(exc.getMessage(), exc);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionResponseBody> handleBadCredentialsExceptionException(BadCredentialsException exc) {
        ExceptionResponseBody error = new ExceptionResponseBody();

        error.setPath("/api/auth/login");
        error.setTimeStamp(ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT));
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.addError(exc.getMessage());

        log.error(exc.getMessage(), exc);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(Forbidden.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<ExceptionResponseBody> handleGenericForbiddenException(Forbidden exc) {
        ExceptionResponseBody error = new ExceptionResponseBody();

        error.setTimeStamp(ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT));
        error.setStatus(HttpStatus.FORBIDDEN.value());
        error.addError("Acesso proibido ao recurso: " + exc.getMessage());

        log.error("Acesso proibido ao recurso", exc);

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionResponseBody> handleUserAlreadyExistsException(UserAlreadyExistsException exc) {
        ExceptionResponseBody error = new ExceptionResponseBody();

        error.setTimeStamp(ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT));
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.addError(exc.getMessage());

        log.error(exc.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(UnauthorizedAcessException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ExceptionResponseBody> handleUnauthorizedAcessExceptionException(UnauthorizedAcessException exc) {
        ExceptionResponseBody error = new ExceptionResponseBody();

        error.setPath(exc.getCause().getMessage());
        error.setTimeStamp(ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT));
        error.setStatus(HttpStatus.UNAUTHORIZED.value());
        error.addError("Acesso não autorizado ao recurso. " + exc.getMessage());

        log.error("Acesso não autorizado ao recurso. {}", exc.getMessage(), exc);

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionResponseBody> handleMethodArgumentNotValidExceptionException(MethodArgumentNotValidException exc) {
        ExceptionResponseBody error = new ExceptionResponseBody();

        error.setTimeStamp(ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT));
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        for(FieldError fieldError : exc.getBindingResult().getFieldErrors()) {
            error.addError(fieldError.getField() + ": " + fieldError.getDefaultMessage());
        }

        log.error(exc.getLocalizedMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(EquipamentNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ExceptionResponseBody> handleEquipamentNotFoundException(EquipamentNotFoundException exc) {
        ExceptionResponseBody error = new ExceptionResponseBody();

        error.setPath("/api/equipament/{id}");
        error.setTimeStamp(ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT));
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.addError(exc.getMessage());

        log.error(exc.getMessage(), exc);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(InactiveEquipamentException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionResponseBody> handleInactiveEquipamentException(InactiveEquipamentException exc) {
        ExceptionResponseBody error = new ExceptionResponseBody();

        error.setPath("/api/equipament/{id}");
        error.setTimeStamp(ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT));
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.addError("Equipamento já encontra-se deletado");

        log.error("Tentativa de deleção de equipamento ja deletado", exc);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(InactiveUserException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionResponseBody> handleInactiveUserException(InactiveUserException exc) {
        ExceptionResponseBody error = new ExceptionResponseBody();

        error.setPath("/api/user/{id}");
        error.setTimeStamp(ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT));
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.addError("Usuário já encontra-se deletado");

        log.error("Tentativa de deleção de usuário ja deletado", exc);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(OSAlreadyOpenedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionResponseBody> handleOSAlreadyOpenedException(OSAlreadyOpenedException exc) {
        ExceptionResponseBody error = new ExceptionResponseBody();

        error.setPath("/api/os/{equipamentoId}");
        error.setTimeStamp(ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT));
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.addError(exc.getMessage());

        log.error(exc.getMessage(), exc);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(OSNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionResponseBody> handleOSNotFoundException(OSNotFoundException exc) {
        ExceptionResponseBody error = new ExceptionResponseBody();

        error.setPath("/api/os/{equipamentoId}/{ordemServicoId}");
        error.setTimeStamp(ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT));
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.addError(exc.getMessage());

        log.error(exc.getMessage(), exc);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

}
