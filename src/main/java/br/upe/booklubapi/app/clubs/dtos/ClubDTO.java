package br.upe.booklubapi.app.clubs.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

public record ClubDTO(
    @NotNull
    UUID id,
    @NotNull
    @Size(max = 50)
    String name,
    @NotNull
    LocalDate creationDate,
    String imageUrl,
    @NotNull
    Boolean isPrivate,
    @NotNull
    UUID ownerId
) implements Serializable {

}