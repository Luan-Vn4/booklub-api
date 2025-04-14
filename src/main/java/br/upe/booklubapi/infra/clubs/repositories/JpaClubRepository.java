package br.upe.booklubapi.infra.clubs.repositories;

import br.upe.booklubapi.domain.clubs.entities.Club;
import br.upe.booklubapi.domain.clubs.entities.QClub;
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
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
@AllArgsConstructor
public abstract class JpaClubRepository
        implements JpaRepository<Club, UUID>, ClubRepository,
            QuerydslPredicateExecutor<Club> {

    private final EntityManager em;

    @Override
    public Page<User> findAllMembers(
        UUID clubId,
        Predicate predicate,
        Pageable pageable
    ) {
        final QClub club = QClub.club;
        final QUser user = QUser.user;
        JPAQuery<User> query = new JPAQuery<>(em);
        long count = count();

        List<User> result = query
            .select(user)
            .from(club)
            .join(club.members, user)
            .where(club.id.eq(clubId).and(predicate))
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        return new PageImpl<>(result, pageable, count);
    }

}
