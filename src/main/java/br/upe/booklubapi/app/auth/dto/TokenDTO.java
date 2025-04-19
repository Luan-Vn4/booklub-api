package br.upe.booklubapi.app.auth.dto;

import java.time.Instant;

public record TokenDTO(
        String accessToken,
        Instant expiration,
        String tokenType) {
}
