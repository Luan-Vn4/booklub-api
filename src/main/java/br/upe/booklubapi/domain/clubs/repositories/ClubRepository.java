package br.upe.booklubapi.domain.clubs.repositories;

import br.upe.booklubapi.domain.clubs.entities.Club;
import br.upe.booklubapi.domain.core.repositories.CrudRepository;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.UUID;

public interface ClubRepository extends CrudRepository<Club, UUID> {

    Page<Club> searchByName(
        @NotNull @Size(max=50) String clubName,
        Pageable pageable
    );

    Page<Club> findAllPublic(Pageable pageable);

    Page<Club> findByOwnerId(UUID ownerId, Pageable pageable);

    Page<Club> findByCreationDateAfter(LocalDate localDate, Pageable pageable);

}
