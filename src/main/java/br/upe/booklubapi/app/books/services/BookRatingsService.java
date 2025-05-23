package br.upe.booklubapi.app.books.services;

import java.util.UUID;

import br.upe.booklubapi.app.books.dtos.BookRatingsDTO;
import br.upe.booklubapi.domain.books.entities.BookUserId;

public interface BookRatingsService {
    BookRatingsDTO findById(UUID userId, UUID bookId);

    BookRatingsDTO save(BookRatingsDTO saveDTO);

    BookRatingsDTO update(BookRatingsDTO dto);

    void delete(UUID userId, UUID bookId);
}
