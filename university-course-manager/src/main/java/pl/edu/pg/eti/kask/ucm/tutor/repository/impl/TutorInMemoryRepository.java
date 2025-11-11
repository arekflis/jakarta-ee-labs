package pl.edu.pg.eti.kask.ucm.tutor.repository.impl;

import jakarta.enterprise.context.RequestScoped;
import pl.edu.pg.eti.kask.ucm.tutor.entity.Tutor;
import pl.edu.pg.eti.kask.ucm.tutor.repository.api.TutorRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestScoped
public class TutorInMemoryRepository implements TutorRepository {

    @Override
    public Optional<Tutor> find(UUID id){
        return null;
    }

    @Override
    public List<Tutor> findAll(){
        return null;
    }

    @Override
    public void create(Tutor entity){ }

    @Override
    public void delete(UUID id) { }

    @Override
    public void update(Tutor entity) { }

    @Override
    public Optional<Tutor> findByEmail(String email){
        return null;
    }

}
