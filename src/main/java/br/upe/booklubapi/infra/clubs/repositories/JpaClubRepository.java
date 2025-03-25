package br.upe.booklubapi.infra.clubs.repositories;

import br.upe.booklubapi.domain.clubs.entities.Club;
import br.upe.booklubapi.domain.clubs.repositories.ClubRepository;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.UUID;

@Repository
public interface JpaClubRepository
        extends JpaRepository<Club, UUID>, ClubRepository {

    @Query("SELECT c FROM Club c WHERE c.name LIKE CONCAT('%', :name, '%') ")
    Page<Club> searchByName(
        @NotNull @Size(max=50) String name,
        Pageable pageable
    );

    @Query("SELECT c FROM Club c WHERE c.isPrivate = false")
    Page<Club> findAllPublic(Pageable pageable);

    Page<Club> findByOwnerId(UUID ownerId, Pageable pageable);

    @Override
    Page<Club> searchByCreationDateBetween(
        LocalDate startDate,
        LocalDate endDate,
        Pageable pageable
    );

}
