package br.upe.booklubapi.app.books.services;

import java.util.UUID;

import br.upe.booklubapi.app.books.dtos.BookClubProgressDTO;
import br.upe.booklubapi.app.books.dtos.BookUserProgressDTO;
import br.upe.booklubapi.domain.books.entities.BookUserId;

public interface BookProgressService {
    BookUserProgressDTO findByUserId(UUID userId, UUID bookId);

    BookUserProgressDTO save(BookUserProgressDTO saveDTO);

    void delete(UUID userId, UUID bookId);

    BookClubProgressDTO findByClubId(UUID clubId, UUID bookId);
}
