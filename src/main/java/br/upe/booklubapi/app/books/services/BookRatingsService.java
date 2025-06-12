package br.upe.booklubapi.app.books.services;

import br.upe.booklubapi.app.books.dtos.bookratings.BookRatingsDTO;
import br.upe.booklubapi.app.books.dtos.bookratings.CreateBookRatingsDTO;
import br.upe.booklubapi.app.books.dtos.bookratings.UpdateBookRatingsDTO;
import br.upe.booklubapi.domain.books.entities.BookUserId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;

import java.util.UUID;

public interface BookRatingsService {

    BookRatingsDTO findById(BookUserId bookUserId);

    PagedModel<BookRatingsDTO> findByUserId(UUID userId, Pageable pageable);

    PagedModel<BookRatingsDTO> findByBookId(String bookId, Pageable pageable);

    BookRatingsDTO save(BookUserId bookUserId, CreateBookRatingsDTO dto);

    BookRatingsDTO update(BookUserId bookUserId, UpdateBookRatingsDTO dto);

    void delete(BookUserId bookUserId);

}
