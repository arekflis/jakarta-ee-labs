package pl.edu.pg.eti.kask.ucm.tutor.repository.api;

import pl.edu.pg.eti.kask.ucm.repository.api.Repository;
import pl.edu.pg.eti.kask.ucm.tutor.entity.Tutor;

import java.util.Optional;
import java.util.UUID;

public interface TutorRepository extends Repository<Tutor, UUID> {

    Optional<Tutor> findByEmail(String email);

    Optional<Tutor> findByLogin(String login);
}
