package br.upe.booklubapi.app.books.services;

import java.util.UUID;

import br.upe.booklubapi.app.books.dtos.BookUserDTO;
import br.upe.booklubapi.domain.books.entities.BookUserId;

public interface BookUserService {
    BookUserDTO findById(UUID userId, UUID bookId);

    BookUserDTO save(BookUserDTO saveDTO);

    BookUserDTO update(BookUserDTO dto);

    void delete(UUID userId, UUID bookId);
}
