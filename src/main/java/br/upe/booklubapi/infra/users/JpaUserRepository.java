package br.upe.booklubapi.infra.users;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import br.upe.booklubapi.domain.clubs.entities.Club;
import br.upe.booklubapi.domain.users.entities.QUser;
import br.upe.booklubapi.domain.users.entities.User;
import br.upe.booklubapi.domain.users.repository.UserRepository;
import br.upe.booklubapi.domain.users.repository.UserRepositoryCustom;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Objects;


@Repository
public interface JpaUserRepository
                extends JpaRepository<User, UUID>, UserRepository, QuerydslPredicateExecutor<User> {

}

@Component
@AllArgsConstructor
class JpaUserRepositoryCustomImpl implements UserRepositoryCustom {
    private EntityManager em;

    @Override
    public Page<User> findByUsernameContaining(String username, Pageable pageable) {
        final QUser user = QUser.user;
        
        Predicate predicate = user.username.startsWithIgnoreCase(username);

        final List<User> result = new JPAQuery<>(em)
            .select(user)
            .from(user)
            .where(predicate)
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();
        
        final long total = new JPAQuery<>(em)
            .select(user.count())
            .from(user)
            .where(predicate)
            .fetchOne();

        return new PageImpl<>(result, pageable, total);
    }
}
