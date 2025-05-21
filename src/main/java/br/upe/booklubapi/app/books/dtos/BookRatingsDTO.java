package br.upe.booklubapi.app.books.dtos;
import java.time.LocalDate;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record BookRatingsDTO(@NotNull UUID userId, @NotNull UUID bookId, @NotNull int dificulty, @NotNull int readability, @NotNull String review, LocalDate createdAt) {
    
}
