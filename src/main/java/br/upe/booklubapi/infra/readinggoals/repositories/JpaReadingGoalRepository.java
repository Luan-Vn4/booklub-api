package br.upe.booklubapi.infra.readinggoals.repositories;

import br.upe.booklubapi.domain.clubs.entities.QClub;
import br.upe.booklubapi.domain.readinggoals.entities.QReadingGoal;
import br.upe.booklubapi.domain.readinggoals.entities.ReadingGoal;
import br.upe.booklubapi.domain.readinggoals.repositories.ReadingGoalRepository;
import br.upe.booklubapi.domain.readinggoals.repositories.UserReadingGoalRepository;
import br.upe.booklubapi.domain.users.entities.QUser;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public interface JpaReadingGoalRepository
        extends JpaRepository<ReadingGoal, UUID>,
                QuerydslPredicateExecutor<ReadingGoal>,
                ReadingGoalRepository,
                UserReadingGoalRepository {

    @Override
    default <S extends ReadingGoal> S save(S entity) {
        return saveAndFlush(entity);
    }

    @Override
    default <S extends ReadingGoal> List<S> saveAll(Iterable<S> entities) {
        return saveAllAndFlush(entities);
    }

    @Override
    @Query("""
        SELECT r FROM ReadingGoal r
            WHERE CURRENT TIMESTAMP BETWEEN r.startDate AND r.endDate
    """)
    Optional<ReadingGoal> findClubCurrentReadingGoal(UUID clubId);

}

@Component
@AllArgsConstructor
class JpaReadingGoalRepositoryCustomImpl implements UserReadingGoalRepository {

    private final EntityManager em;

    @Override
    public Page<ReadingGoal> findUserReadingGoals(
        UUID userId,
        Predicate predicate,
        Pageable pageable
    ) {
        final QUser user = QUser.user;
        final QClub club = QClub.club;
        final QReadingGoal readingGoal = QReadingGoal.readingGoal;

        final Long count = new JPAQuery<>(em)
            .select(readingGoal.count())
            .from(user)
            .where(user.id.eq(userId))
            .join(club)
            .on(club.members.contains(user))
            .join(readingGoal)
            .on(readingGoal.club.id.eq(club.id).and(predicate))
            .fetchOne();

        final List<ReadingGoal> results = new JPAQuery<>(em)
            .select(readingGoal)
            .from(user)
            .where(user.id.eq(userId))
            .join(club)
            .on(club.members.contains(user))
            .join(readingGoal)
            .on(readingGoal.club.id.eq(club.id).and(predicate))
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        return new PageImpl<>(
            results,
            pageable,
            Objects.requireNonNullElse(count, 0L)
        );
    }

}
