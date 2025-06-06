package br.upe.booklubapi.domain.activities.repositories;

import br.upe.booklubapi.domain.activities.entities.clubactivities.ClubActivity;
import br.upe.booklubapi.domain.core.repositories.CrudRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ClubActivityRepository
        extends CrudRepository<ClubActivity, UUID> {

    Page<ClubActivity> findAllByClubId(UUID clubId, Pageable pageable);

}
