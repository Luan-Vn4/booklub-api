package br.upe.booklubapi.app.user.dtos;

import jakarta.annotation.Nullable;

public record UpdateUserDTO(
    @Nullable
    String username,
    @Nullable
    String email,
    @Nullable
    String firstName,
    @Nullable
    String lastName,
    @Nullable
    String imageUrl
) {}