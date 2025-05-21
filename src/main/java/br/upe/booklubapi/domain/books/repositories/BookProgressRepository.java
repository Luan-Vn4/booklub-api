package br.upe.booklubapi.domain.books.repositories;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import br.upe.booklubapi.domain.books.entities.BookUserId;
import br.upe.booklubapi.domain.books.entities.BookUser;
import br.upe.booklubapi.domain.core.repositories.CrudRepository;
import jakarta.annotation.Nullable;

@Repository
public interface BookProgressRepository extends CrudRepository<BookUser, BookUserId> {
    @Nullable Double findByClubId(UUID userId, UUID bookId);
}
