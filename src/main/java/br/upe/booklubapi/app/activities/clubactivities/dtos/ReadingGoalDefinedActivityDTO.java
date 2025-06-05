package br.upe.booklubapi.app.activities.clubactivities.dtos;

import br.upe.booklubapi.app.activities.dtos.ClubActivityDTO;

import java.time.LocalDateTime;
import java.util.UUID;

public record ReadingGoalDefinedActivityDTO(
    UUID id,
    LocalDateTime createdAt,
    UUID clubId,
    UUID readingGoalId
) implements ClubActivityDTO {
}
