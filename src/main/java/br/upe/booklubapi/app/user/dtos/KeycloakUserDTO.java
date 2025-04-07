package br.upe.booklubapi.app.user.dtos;

import java.util.List;
import java.util.Map;

public record KeycloakUserDTO(
    String id,
    String username,
    String email,
    String firstName,
    String lastName,
    boolean enabled,
    boolean emailVerified,
    Map<String, List<String>> attributes
) {}

