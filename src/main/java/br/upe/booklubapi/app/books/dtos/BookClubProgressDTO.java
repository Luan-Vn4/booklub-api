package br.upe.booklubapi.app.books.dtos;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record BookClubProgressDTO(@NotNull UUID clubId, @NotNull UUID bookId, @NotNull Double progress) {
    
}
