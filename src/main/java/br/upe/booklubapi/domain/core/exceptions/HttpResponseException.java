package br.upe.booklubapi.domain.core.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.Instant;

@Getter
public class HttpResponseException extends RuntimeException {

    private final HttpStatus status;

    private final String error;

    private final Instant timestamp;

    public HttpResponseException(
        HttpStatus status,
        String error,
        String message
    ) {
        super(message);
        this.error = error;
        this.status = status;
        this.timestamp = Instant.now();
    }

}
