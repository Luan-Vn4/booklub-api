package br.upe.booklubapi.domain.core.repositories;

public interface CrudRepository<T, ID>
        extends ReadableRepository<T, ID>, PersistableRepository<T>,
        RemovableRepository<T, ID> {}
