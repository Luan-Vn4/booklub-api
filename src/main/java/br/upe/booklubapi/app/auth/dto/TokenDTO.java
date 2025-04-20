package br.upe.booklubapi.app.auth.dto;

import java.time.ZonedDateTime;

public record TokenDTO(
        String accessToken,
        ZonedDateTime expiration,
        String tokenType) {
}
