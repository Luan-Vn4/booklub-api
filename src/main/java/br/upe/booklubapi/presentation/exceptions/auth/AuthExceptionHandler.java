package br.upe.booklubapi.presentation.exceptions.auth;

import br.upe.booklubapi.domain.auth.exceptions.PermissionDeniedException;
import br.upe.booklubapi.presentation.exceptions.core.ExceptionBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import java.time.Instant;

@ControllerAdvice
public class AuthExceptionHandler {

    @ExceptionHandler(WebClientResponseException.class)
    public ResponseEntity<ExceptionBody> handle(
        WebClientResponseException exception
    ) {
        var body = ExceptionBody.builder()
            .httpStatus(exception.getStatusCode().value())
            .error("Auth Server Response Error")
            .message(exception.getMessage())
            .timestamp(Instant.now())
            .build();

        return ResponseEntity.status(exception.getStatusCode()).body(body);
    }

    @ExceptionHandler(WebClientRequestException.class)
    public ResponseEntity<ExceptionBody> handle(
        WebClientRequestException exception
    ) {
        final HttpStatus status = HttpStatus.SERVICE_UNAVAILABLE;

        var body = ExceptionBody.builder()
            .httpStatus(status.value())
            .error("Auth Server Request Error")
            .message(exception.getMessage())
            .timestamp(Instant.now())
            .build();

        return ResponseEntity.status(status).body(body);
    }

    @ExceptionHandler(PermissionDeniedException.class)
    public ResponseEntity<ExceptionBody> handle(
        PermissionDeniedException exception
    ) {
        final HttpStatus status = HttpStatus.FORBIDDEN;

        var body = ExceptionBody.builder()
            .httpStatus(status.value())
            .error("Não possui permissão para executar comando")
            .message(exception.getMessage())
            .timestamp(Instant.now())
            .build();

        return ResponseEntity.status(status).body(body);
    }

}
