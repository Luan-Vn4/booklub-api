package br.upe.booklubapi.infra.activities.repositories;

import br.upe.booklubapi.domain.activities.entities.useractivities.UserActivity;
import br.upe.booklubapi.domain.activities.repositories.UserActivityRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface JpaUserActivityRepository
        extends JpaRepository<UserActivity, UUID>, UserActivityRepository {

    @Override
    Page<UserActivity> findAllByUserId(UUID userId, Pageable pageable);

}
