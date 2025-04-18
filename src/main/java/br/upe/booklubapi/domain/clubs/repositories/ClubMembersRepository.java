package br.upe.booklubapi.domain.clubs.repositories;

import br.upe.booklubapi.domain.clubs.entities.Club;
import br.upe.booklubapi.domain.users.entities.User;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ClubMembersRepository {

    /**
     * Busca todos os membros de um clube
     * @param clubId Identificador do clube
     * @param predicate {@link Predicate} para filtrar os usuários retornados
     * @return membros do clube, conforme os filtros aplicados
     */
    Page<User> findAllClubMembers(
        UUID clubId,
        Predicate predicate,
        Pageable pageable
    );

    Page<Club> findAllUserClubs(
        UUID userId,
        Predicate predicate,
        Pageable pageable
    );

}
