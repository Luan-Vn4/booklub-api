package br.upe.booklubapi.infra.clubs.repositories;

import br.upe.booklubapi.domain.clubs.entities.Club;
import br.upe.booklubapi.domain.clubs.entities.QClub;
import br.upe.booklubapi.domain.clubs.repositories.ClubMembersRepository;
import br.upe.booklubapi.domain.clubs.repositories.ClubRepository;
import br.upe.booklubapi.domain.users.entities.QUser;
import br.upe.booklubapi.domain.users.entities.User;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Repository
public interface JpaClubRepository
        extends JpaRepository<Club, UUID>, ClubRepository, ClubMembersRepository,
            QuerydslPredicateExecutor<Club> {}

@Component
@AllArgsConstructor
class JpaClubRepositoryCustomImpl implements ClubMembersRepository {

    private EntityManager em;

    @Override
    public Page<User> findAllClubMembers(
        UUID clubId,
        Predicate predicate,
        Pageable pageable
    ) {
        final QClub club = QClub.club;
        final QUser user = QUser.user;

        final Long count = new JPAQuery<>(em)
            .select(user.count())
            .from(club)
            .where(club.id.eq(clubId))
            .join(club.members, user)
            .on(predicate)
            .fetchOne();

        final List<User> result = new JPAQuery<>(em)
            .select(user)
            .from(club)
            .where(club.id.eq(clubId))
            .join(club.members, user)
            .on(predicate)
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        return new PageImpl<>(
            result,
            pageable,
            Objects.requireNonNullElse(count, 0L)
        );
    }

    @Override
    public Page<Club> findAllUserClubs(
        UUID userId,
        Predicate predicate,
        Pageable pageable
    ) {
        final QClub club = QClub.club;
        final QUser user = QUser.user;

        final Long count = new JPAQuery<>(em)
            .select(club.count())
            .from(user)
            .where(user.id.eq(userId))
            .join(user.clubs, club)
            .on(user.clubs.contains(club).and(predicate))
            .fetchOne();

        final List<Club> result = new JPAQuery<>(em)
            .select(club)
            .from(user)
            .where(user.id.eq(userId))
            .join(user.clubs, club)
            .on(user.clubs.contains(club).and(predicate))
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        return new PageImpl<>(
            result,
            pageable,
            Objects.requireNonNullElse(count, 0L)
        );
    }

}
