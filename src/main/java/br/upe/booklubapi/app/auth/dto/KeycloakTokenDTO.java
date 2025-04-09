package br.upe.booklubapi.app.auth.dto;

public record KeycloakTokenDTO(
    String access_token,
    String expires_in,
    String refresh_expires_in,
    String refresh_token,
    String token_type,
    String not_before_policy,
    String session_state,
    String scope
) {}
