package pl.edu.pg.eti.kask.ucm.tutor.service.api;

import pl.edu.pg.eti.kask.ucm.service.api.Service;
import pl.edu.pg.eti.kask.ucm.tutor.entity.Tutor;

import java.util.Optional;
import java.util.UUID;

public interface TutorService extends Service<Tutor, UUID> {

    Optional<Tutor> findByEmail(String email);
}
