package br.upe.booklubapi.domain.activities.repositories;

import br.upe.booklubapi.domain.activities.entities.Activity;
import br.upe.booklubapi.domain.activities.entities.clubactivities.ClubActivity;
import br.upe.booklubapi.domain.activities.entities.useractivities.UserActivity;
import br.upe.booklubapi.domain.core.repositories.CrudRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.UUID;

public interface ActivityRepository extends CrudRepository<Activity, UUID> {

    Page<Activity> findActivitiesForUser(UUID userId, Pageable pageable);

    Page<ClubActivity> findAllByClubId(UUID clubId, Pageable pageable);

    Page<UserActivity> findAllByUserId(UUID userId, Pageable pageable);

}
