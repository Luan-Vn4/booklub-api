package br.upe.booklubapi.domain.books.repositories;

import br.upe.booklubapi.domain.books.entities.BookUser;
import br.upe.booklubapi.domain.books.entities.BookUserId;
import br.upe.booklubapi.domain.core.repositories.CrudRepository;
import jakarta.annotation.Nullable;

import java.util.UUID;

public interface BookProgressRepository
        extends CrudRepository<BookUser, BookUserId> {

    @Nullable Double findByClubId(UUID userId, String bookId);

}
