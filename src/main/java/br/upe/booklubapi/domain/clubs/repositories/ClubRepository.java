package br.upe.booklubapi.domain.clubs.repositories;

import br.upe.booklubapi.domain.clubs.entities.Club;
import br.upe.booklubapi.domain.core.repositories.CrudRepository;
import br.upe.booklubapi.domain.core.repositories.QueryableRepository;
import br.upe.booklubapi.domain.users.entities.User;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface ClubRepository
    extends CrudRepository<Club, UUID>, QueryableRepository<Club> {

    /**
     * Busca todos os membros de um clube
     * @param clubId Identificador do clube
     * @param predicate {@link Predicate} para filtrar os usuários retornados
     * @return membros do clube, conforme os filtros aplicados
     */
    Page<User> findAllMembers(
        UUID clubId,
        Predicate predicate,
        Pageable pageable
    );

}
