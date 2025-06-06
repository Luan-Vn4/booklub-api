package br.upe.booklubapi.domain.activities.repositories;

import br.upe.booklubapi.domain.activities.entities.Activity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.UUID;

public interface ActivitiesForUserRepository {

    Page<Activity> findActivitiesForUser(UUID userId, Pageable pageable);

}
