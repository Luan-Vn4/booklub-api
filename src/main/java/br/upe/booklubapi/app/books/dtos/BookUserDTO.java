package br.upe.booklubapi.app.books.dtos;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record BookUserDTO(@NotNull UUID userId, @NotNull UUID bookId, @NotNull Double progress) {
    
}
