package br.upe.booklubapi.app.clubs.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record CreateClubDTO(
    @NotNull
    @Size(max = 50)
    String name,
    String imageUrl,
    @NotNull
    Boolean isPrivate,
    UUID ownerId
) {}