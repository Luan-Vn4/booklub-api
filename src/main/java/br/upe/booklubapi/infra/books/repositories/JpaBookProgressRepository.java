package br.upe.booklubapi.infra.books.repositories;

import br.upe.booklubapi.domain.books.entities.BookUserProgress;
import br.upe.booklubapi.domain.books.entities.BookUserId;
import br.upe.booklubapi.domain.books.repositories.BookProgressRepository;
import br.upe.booklubapi.domain.clubs.entities.ClubPendingEntry;
import br.upe.booklubapi.domain.clubs.entities.enums.EntryType;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaBookProgressRepository
        extends JpaRepository<BookUserProgress, BookUserId>, BookProgressRepository {
    @Query(value = """
                SELECT AVG(bup.progress)
                FROM clubs c
                JOIN user_entity u ON c.owner_id = u.id
                JOIN book_user_progress bup ON bup.user_id = u.id
                WHERE c.id = :clubId
                AND bup.book_id = :bookId
            """, nativeQuery = true)
    @Override
    Double findByClubId(UUID clubId, UUID bookId);

}
