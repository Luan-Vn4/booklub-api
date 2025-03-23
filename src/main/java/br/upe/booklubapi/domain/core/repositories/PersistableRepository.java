package br.upe.booklubapi.domain.core.repositories;

public interface PersistableRepository<T> {

    <S extends T> S save(S entity);

    <S extends T> Iterable<S> saveAll(Iterable<S> entities);

}
