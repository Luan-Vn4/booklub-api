package br.upe.booklubapi.app.books.dtos.bookratings;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;
import java.util.UUID;

public record CreateBookRatingsDTO(
    @NotNull
    @Min(1)
    @Max(5)
    Short rating,
    @NotNull
    @Min(1)
    @Max(5)
    Short difficulty,
    @Size(max = 500)
    String review
) implements Serializable {}