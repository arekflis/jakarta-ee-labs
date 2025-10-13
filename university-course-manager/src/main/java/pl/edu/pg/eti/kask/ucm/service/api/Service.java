package pl.edu.pg.eti.kask.ucm.service.api;

import java.util.List;
import java.util.Optional;

/*
    E - Entity
    K - Key (primary)
 */
public interface Service<E, K> {

    Optional<E> find(K id);

    List<E> findAll();

    void create(E entity);

    void delete(E entity);

    void update(E entity);
}
