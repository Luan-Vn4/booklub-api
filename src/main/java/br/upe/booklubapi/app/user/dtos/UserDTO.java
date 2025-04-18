package br.upe.booklubapi.app.user.dtos;

import java.util.UUID;

public record UserDTO(
    UUID id,
    String username,
    String email,
    String firstName,
    String lastName,
    String imageUrl
) {}