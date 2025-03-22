package br.upe.booklubapi.presentation.exceptions.core;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder
public class ExceptionBody {
    private int httpStatus;
    private String error;
    private String message;
    private Instant timestamp;
}
