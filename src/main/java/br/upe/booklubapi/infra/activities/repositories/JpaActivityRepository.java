package br.upe.booklubapi.infra.activities.repositories;

import br.upe.booklubapi.domain.activities.entities.Activity;
import br.upe.booklubapi.domain.activities.repositories.ActivityRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.UUID;

public interface JpaActivityRepository
        extends JpaRepository<Activity, UUID>, ActivityRepository {

    @Override
    default <S extends Activity> S save(S entity) {
        return saveAndFlush(entity);
    }

    @Override
    default <S extends Activity> List<S> saveAll(Iterable<S> entities) {
        return saveAllAndFlush(entities);
    }

    @Override
    @Query("""
        SELECT a FROM Activity a
            WHERE (TYPE(a) = UserActivity AND TREAT(a AS UserActivity).user.id=:userId)
                OR (TYPE(a) = ClubActivity AND TREAT(a AS ClubActivity).club IN (
                    SELECT c FROM User u JOIN u.clubs c WITH u.id=:userId
                ))
    """)
    Page<Activity> findActivitiesForUser(UUID userId, Pageable pageable);

}
