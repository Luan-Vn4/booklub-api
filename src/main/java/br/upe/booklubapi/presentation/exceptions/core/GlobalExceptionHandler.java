package br.upe.booklubapi.presentation.exceptions.core;

import java.time.Instant;
import java.util.Map;
import java.util.stream.Collectors;

import br.upe.booklubapi.domain.auth.exceptions.PermissionDeniedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

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

    @ExceptionHandler(WebClientResponseException.class)
    public ResponseEntity<String> handleWebClientResponseException(WebClientResponseException exception) {
        return ResponseEntity
                .status(exception.getStatusCode())
                .body("Erro ao requisitar o server de autênticação: " + exception.getResponseBodyAsString());
    }

    @ExceptionHandler(WebClientRequestException.class)
    public ResponseEntity<String> handleWebClientRequestException(WebClientRequestException exception) {
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Erro na comunicação com o server de autênticação: " + exception.getMessage());
    }

    @ExceptionHandler(PermissionDeniedException.class)
    public ResponseEntity<ExceptionBody> handleWebClientRequestException(PermissionDeniedException exception) {
        ExceptionBody body = ExceptionBody.builder()
        .httpStatus(HttpStatus.BAD_REQUEST.value())
        .error("Não possui permissão para executar comando")
        .message(exception.getMessage())
        .timestamp(Instant.now())
        .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }



}