package br.upe.booklubapi.domain.clubs.repositories;

import br.upe.booklubapi.domain.clubs.entities.ClubPendingEntry;
import br.upe.booklubapi.domain.clubs.entities.ClubPendingEntryId;
import br.upe.booklubapi.domain.clubs.entities.enums.EntryType;
import br.upe.booklubapi.domain.core.repositories.CrudRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.UUID;

public interface ClubPendingEntryRepository
    extends CrudRepository<ClubPendingEntry, ClubPendingEntryId> {

    Page<ClubPendingEntry> findByClubId(UUID clubId, Pageable pageable);

    Page<ClubPendingEntry> findByClubId(
        UUID clubId,
        Pageable pageable,
        EntryType entryType
    );

    Page<ClubPendingEntry> findByUserId(UUID userId, Pageable pageable);

    Page<ClubPendingEntry> findByUserId(
        UUID userId,
        Pageable pageable,
        EntryType entryType
    );

}
