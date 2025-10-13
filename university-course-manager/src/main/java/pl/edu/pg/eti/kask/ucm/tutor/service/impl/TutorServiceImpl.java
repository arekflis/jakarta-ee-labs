package pl.edu.pg.eti.kask.ucm.tutor.service.impl;

import pl.edu.pg.eti.kask.ucm.tutor.entity.Tutor;
import pl.edu.pg.eti.kask.ucm.tutor.repository.api.TutorRepository;
import pl.edu.pg.eti.kask.ucm.tutor.service.api.TutorService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class TutorServiceImpl implements TutorService {

    private final TutorRepository repository;

    public TutorServiceImpl(TutorRepository repository){
        this.repository = repository;
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
}
