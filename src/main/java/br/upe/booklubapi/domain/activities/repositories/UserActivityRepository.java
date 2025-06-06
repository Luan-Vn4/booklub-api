package br.upe.booklubapi.domain.activities.repositories;

import br.upe.booklubapi.domain.activities.entities.useractivities.UserActivity;
import br.upe.booklubapi.domain.core.repositories.CrudRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.UUID;

public interface UserActivityRepository
        extends CrudRepository<UserActivity, UUID> {

    Page<UserActivity> findAllByUserId(UUID userId, Pageable pageable);

}
