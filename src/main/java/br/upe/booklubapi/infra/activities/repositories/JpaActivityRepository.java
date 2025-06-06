package br.upe.booklubapi.infra.activities.repositories;

import br.upe.booklubapi.domain.activities.entities.Activity;
import br.upe.booklubapi.domain.activities.entities.QActivity;
import br.upe.booklubapi.domain.activities.entities.clubactivities.QClubActivity;
import br.upe.booklubapi.domain.activities.entities.useractivities.QUserActivity;
import br.upe.booklubapi.domain.activities.repositories.ActivitiesForUserRepository;
import br.upe.booklubapi.domain.activities.repositories.ActivityRepository;
import br.upe.booklubapi.domain.clubs.entities.Club;
import br.upe.booklubapi.domain.clubs.entities.QClub;
import br.upe.booklubapi.domain.users.entities.QUser;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public interface JpaActivityRepository
        extends JpaRepository<Activity, UUID>,
                ActivityRepository,
                ActivitiesForUserRepository {

    @NotNull
    @Override
    default <S extends Activity> S save(@NotNull S entity) {
        return saveAndFlush(entity);
    }

    @NotNull
    @Override
    default <S extends Activity> List<S> saveAll(Iterable<S> entities) {
        return saveAllAndFlush(entities);
    }

}

@Component
@AllArgsConstructor
class JpaActivityRepositoryCustomImpl {

    private final EntityManager em;

    public Page<Activity> findActivitiesForUser(UUID userId, Pageable pageable) {
        QUser user = QUser.user;
        QActivity activity = QActivity.activity;
        QUserActivity userActivity = new QUserActivity("ua");
        QClubActivity clubActivity = new QClubActivity("ca");
        QClub club = QClub.club;

        final JPAQuery<Club> clubsUserParticipates = new JPAQuery<>(em)
            .select(club)
            .from(user)
            .where(user.id.eq(userId))
            .join(user.clubs, club);

        final Long total = new JPAQuery<>(em)
            .select(activity.count())
            .from(activity)
            .leftJoin(userActivity)
            .on(
                userActivity.user.id.eq(userId)
            )
            .leftJoin(clubActivity)
            .on(
                clubActivity.club.in(clubsUserParticipates)
            ).fetchOne();

        final List<Activity> activities = new JPAQuery<>(em)
            .select(activity)
            .from(activity)
            .leftJoin(userActivity)
            .on(
                userActivity.user.id.eq(userId)
            )
            .leftJoin(clubActivity)
            .on(
                clubActivity.club.in(clubsUserParticipates)
            ).fetch();

        return new PageImpl<>(
            activities,
            pageable,
            Objects.requireNonNullElse(total, 0L)
        );
    }

}
