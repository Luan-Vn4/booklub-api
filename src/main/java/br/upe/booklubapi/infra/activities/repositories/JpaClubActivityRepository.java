package br.upe.booklubapi.infra.activities.repositories;

import br.upe.booklubapi.domain.activities.entities.clubactivities.ClubActivity;
import br.upe.booklubapi.domain.activities.repositories.ClubActivityRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaClubActivityRepository
        extends JpaRepository<ClubActivity, UUID>, ClubActivityRepository {

    @Override
    Page<ClubActivity> findAllByClubId(UUID clubId, Pageable pageable);

}
