package br.upe.booklubapi.presentation.exceptions.core;

import java.time.Instant;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import br.upe.booklubapi.domain.core.exceptions.HttpResponseException;
import br.upe.booklubapi.domain.core.exceptions.IllegalQueryException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import br.upe.booklubapi.utils.JsonUtils;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(MethodArgumentNotValidException.class)  
    public ResponseEntity<ExceptionBody> handle(
        MethodArgumentNotValidException exception
    ) {
        final HttpStatus status = HttpStatus.BAD_REQUEST;

        Map<String, String> map = exception.getBindingResult().getFieldErrors()
            .stream()
            .collect(Collectors.toMap(
                FieldError::getField,
                fieldError -> Objects.requireNonNullElse(
                    fieldError.getDefaultMessage(),
                    ""
                ),
                (existing, replacement) -> existing
            ));
        
        var body = ExceptionBody.builder()
            .httpStatus(status.value())
            .error("Invalid Entity Fields")
            .message(JsonUtils.convertMapToJson(map))
            .timestamp(Instant.now())
            .build();

        return ResponseEntity.status(status).body(body);
    }

    @ExceptionHandler(IllegalQueryException.class)
    public ResponseEntity<ExceptionBody> handle(IllegalQueryException e) {
        final HttpStatus status = HttpStatus.CONFLICT;

        final var resp = ExceptionBody.builder()
            .httpStatus(status.value())
            .error("Illegal Query")
            .message(e.getMessage())
            .timestamp(Instant.now())
            .build();

        return ResponseEntity.status(status).body(resp);
    }

    @ExceptionHandler(HttpResponseException.class)
    public ResponseEntity<ExceptionBody> handle(HttpResponseException e) {
        final var resp = ExceptionBody.builder()
            .httpStatus(e.getStatus().value())
            .error(e.getError())
            .message(e.getMessage())
            .timestamp(e.getTimestamp())
            .build();
        return ResponseEntity.status(e.getStatus()).body(resp);
    }


}