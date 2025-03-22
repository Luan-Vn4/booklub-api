package br.upe.booklubapi.presentation.exceptions;

import java.time.Instant;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.upe.booklubapi.presentation.exceptions.core.ExceptionBody;
import br.upe.booklubapi.utils.JsonUtils;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(MethodArgumentNotValidException.class)  
    public ResponseEntity<ExceptionBody> handleInvalidEntityFields(MethodArgumentNotValidException exception) {
        Map<String, String> map = exception.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(
                    fieldError -> fieldError.getField(),
                    fieldError -> fieldError.getDefaultMessage(),
                    (existing, replacement) -> existing
                ));
        
        ExceptionBody body = ExceptionBody.builder()
            .httpStatus(HttpStatus.BAD_REQUEST.value())
            .error("Campo(s) inválido(s)")
            .message(JsonUtils.convertMapToJson(map))
            .timestamp(Instant.now())
            .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }
}