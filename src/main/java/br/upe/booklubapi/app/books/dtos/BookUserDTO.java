package br.upe.booklubapi.app.books.dtos;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record BookUserDTO(
    @NotNull
    UUID userId,
    @NotNull
    String bookId,
    @NotNull
    @DecimalMax("1")
    @DecimalMin("0")
    Double progress
) {}
