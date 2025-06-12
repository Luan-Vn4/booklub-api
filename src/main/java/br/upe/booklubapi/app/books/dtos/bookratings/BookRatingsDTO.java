package br.upe.booklubapi.app.books.dtos.bookratings;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record BookRatingsDTO(
    @NotNull
    UUID userId,
    @NotNull
    String bookId,
    @NotNull
    @Min(1)
    @Max(5)
    Integer difficulty,
    @Nullable
    String review,
    @NotNull
    LocalDateTime createdAt
) {}
