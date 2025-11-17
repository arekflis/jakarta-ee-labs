package pl.edu.pg.eti.kask.ucm.tutor.service.api;

import jakarta.ejb.Local;
import pl.edu.pg.eti.kask.ucm.service.api.Service;
import pl.edu.pg.eti.kask.ucm.tutor.entity.Tutor;

import java.io.InputStream;
import java.util.Optional;
import java.util.UUID;

@Local
public interface TutorService extends Service<Tutor, UUID> {

    Optional<Tutor> findByEmail(String email);

    byte[] getAvatar(UUID id);
    void putAvatar(UUID id, InputStream is);
    void deleteAvatar(UUID id);
    void patchAvatar(UUID id, InputStream is);
}
