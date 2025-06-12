package br.upe.booklubapi.infra.books.repositories;

import br.upe.booklubapi.domain.books.entities.BookUser;
import br.upe.booklubapi.domain.books.entities.BookUserId;
import br.upe.booklubapi.domain.books.repositories.BookProgressRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaBookProgressRepository
        extends JpaRepository<BookUser, BookUserId>, BookProgressRepository {

    @Query(value = """
                SELECT AVG(bu.progress)
                FROM clubs c
                JOIN user_entity u ON c.owner_id = u.id
                JOIN book_user bu ON bu.user_id = u.id
                WHERE c.id = :clubId
                AND bu.book_id = :bookId
            """, nativeQuery = true)
    @Override
    Double findByClubId(UUID clubId, String bookId);

}
