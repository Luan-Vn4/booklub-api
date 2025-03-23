package br.upe.booklubapi.domain.core.repositories;

public interface RemovableRepository<T, ID> {

    void deleteById(ID id);

    void delete(T entity);

    void deleteAllById(Iterable<? extends ID> ids);

    void deleteAll(Iterable<? extends T> entities);

    void deleteAll();

}
