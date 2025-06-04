package br.upe.booklubapi.app.readinggoals.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

public record UpdateReadingGoalDTO(
    @NotEmpty
    @Size(max = 32)
    String bookId,
    @NotNull
    LocalDate startDate,
    @NotNull
    LocalDate endDate
) implements Serializable {}