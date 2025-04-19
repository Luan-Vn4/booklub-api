package br.upe.booklubapi.presentation.exceptions.core;

import java.time.Instant;
import lombok.Builder;

@Builder
public record ExceptionBody(
    int httpStatus,
    String error,
    String message,
    Instant timestamp
) {}
