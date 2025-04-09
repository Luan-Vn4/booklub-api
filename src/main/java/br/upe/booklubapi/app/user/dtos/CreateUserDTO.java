package br.upe.booklubapi.app.user.dtos;

public record CreateUserDTO(
    String username,
    String email,
    String firstName,
    String lastName,
    String imageUrl,
    String password
) {}