package br.upe.booklubapi.app.books.services;

import br.upe.booklubapi.app.books.dtos.BookUserDTO;

import java.util.UUID;

public interface BookUserService {

    BookUserDTO findById(UUID userId, String bookId);

    BookUserDTO save(BookUserDTO saveDTO);

    BookUserDTO update(BookUserDTO dto);

    void delete(UUID userId, String bookId);

}
