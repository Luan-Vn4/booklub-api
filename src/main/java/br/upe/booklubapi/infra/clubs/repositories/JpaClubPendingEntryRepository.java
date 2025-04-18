package br.upe.booklubapi.infra.clubs.repositories;

import br.upe.booklubapi.domain.clubs.entities.ClubPendingEntry;
import br.upe.booklubapi.domain.clubs.entities.ClubPendingEntryId;
import br.upe.booklubapi.domain.clubs.entities.enums.EntryType;
import br.upe.booklubapi.domain.clubs.repositories.ClubPendingEntryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface JpaClubPendingEntryRepository
        extends JpaRepository<ClubPendingEntry, ClubPendingEntryId>,
                ClubPendingEntryRepository {

    @Override
    Page<ClubPendingEntry> findByClubId(UUID clubId, Pageable pageable);

    @Override
    @Query("""
        SELECT c FROM ClubPendingEntry c
            WHERE c.club.id=:clubId
            AND c.entryType=:entryType
    """)
    Page<ClubPendingEntry> findByClubId(
        UUID clubId,
        Pageable pageable,
        EntryType entryType
    );

    @Override
    Page<ClubPendingEntry> findByUserId(UUID userId, Pageable pageable);

    @Override
    @Query("""
        SELECT c FROM ClubPendingEntry c
            WHERE c.club.id=:clubId
                AND c.entryType=:entryType
    """)
    Page<ClubPendingEntry> findByUserId(
        UUID clubId,
        Pageable pageable,
        EntryType entryType
    );

}
