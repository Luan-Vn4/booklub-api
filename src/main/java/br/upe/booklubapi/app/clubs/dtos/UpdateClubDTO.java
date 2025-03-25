package br.upe.booklubapi.app.clubs.dtos;

import jakarta.validation.constraints.Size;
import java.io.Serializable;

public record UpdateClubDTO(
    @Size(max = 50)
    String name,
    String imageUrl,
    Boolean isPrivate
) implements Serializable {}