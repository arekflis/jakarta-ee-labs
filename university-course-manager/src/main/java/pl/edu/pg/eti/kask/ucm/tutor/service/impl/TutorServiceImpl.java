package pl.edu.pg.eti.kask.ucm.tutor.service.impl;

import pl.edu.pg.eti.kask.ucm.controller.servlet.exception.NotFoundException;
import pl.edu.pg.eti.kask.ucm.tutor.entity.Tutor;
import pl.edu.pg.eti.kask.ucm.tutor.repository.api.TutorRepository;
import pl.edu.pg.eti.kask.ucm.tutor.service.api.TutorService;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class TutorServiceImpl implements TutorService {

    private final TutorRepository repository;
    private final String avatarUploadPath;

    public TutorServiceImpl(TutorRepository repository, String avatarUploadPath){
        this.repository = repository;
        this.avatarUploadPath = avatarUploadPath;
    }

    @Override
    public Optional<Tutor> find(UUID id) {
        return this.repository.find(id);
    }

    @Override
    public Optional<Tutor> findByEmail(String email){
        return this.repository.findByEmail(email);
    }

    @Override
    public List<Tutor> findAll() {
        return this.repository.findAll();
    }

    @Override
    public void delete(UUID id) {
        this.repository.delete(id);
    }

    @Override
    public void create(Tutor entity) {
        this.repository.create(entity);
    }

    @Override
    public void update(Tutor entity){
        this.repository.update(entity);
    }

    @Override
    public byte[] getAvatar(UUID id){
        return this.repository.find(id)
                .map(tutor -> {
                    if (tutor.getAvatar() != null && !tutor.getAvatar().isEmpty()) {
                        try {
                            Path avatarPath = Paths.get(avatarUploadPath, tutor.getAvatar());
                            return Files.readAllBytes(avatarPath);
                        } catch (IOException ex) {
                            throw new IllegalStateException("Unable to read avatar file", ex);
                        }
                    }
                    return null;
                })
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public void putAvatar(UUID id, InputStream is) {
        this.repository.find(id).ifPresent(
                tutor -> {
                    try {
                        Path dirPath = Paths.get(avatarUploadPath);

                        String fileName = id.toString() + ".jpg";
                        Path filePath = dirPath.resolve(fileName);
                        Files.copy(is, filePath, StandardCopyOption.REPLACE_EXISTING);

                        tutor.setAvatar(fileName);
                        this.repository.update(tutor);
                    }
                    catch (IOException ex) {
                        throw new IllegalStateException(ex);
                    }
                }
        );
    }

    @Override
    public void deleteAvatar(UUID id) {
        this.repository.find(id).ifPresentOrElse(
                tutor -> {
                    if (tutor.getAvatar() != null) {
                        try {
                            Path dirPath = Paths.get(avatarUploadPath);
                            Path avatarPath = dirPath.resolve(tutor.getAvatar());
                            Files.deleteIfExists(avatarPath);

                            tutor.setAvatar(null);
                            this.repository.update(tutor);
                        } catch (IOException ex) {
                            throw new IllegalStateException("Unable to read avatar file", ex);
                        }
                    }
                    else {
                        throw new NotFoundException();
                    }
                },
                () -> {
                    throw new NotFoundException();
                }
        );
    }

    @Override
    public void patchAvatar(UUID id, InputStream is) {
        this.repository.find(id).ifPresent(
                tutor -> {
                    try {
                        Path dirPath = Paths.get(avatarUploadPath);

                        String fileName = id.toString() + ".jpg";
                        Path filePath = dirPath.resolve(fileName);
                        Files.copy(is, filePath, StandardCopyOption.REPLACE_EXISTING);

                        tutor.setAvatar(fileName);
                        this.repository.update(tutor);
                    }
                    catch (IOException ex) {
                        throw new IllegalStateException(ex);
                    }
                }
        );
    }
}
