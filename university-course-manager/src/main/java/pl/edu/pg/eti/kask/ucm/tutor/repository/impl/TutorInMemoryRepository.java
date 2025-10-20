package pl.edu.pg.eti.kask.ucm.tutor.repository.impl;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import pl.edu.pg.eti.kask.ucm.datastore.component.DataStore;
import pl.edu.pg.eti.kask.ucm.tutor.entity.Tutor;
import pl.edu.pg.eti.kask.ucm.tutor.repository.api.TutorRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestScoped
public class TutorInMemoryRepository implements TutorRepository {

    private final DataStore dataStore;

    @Inject
    public TutorInMemoryRepository(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public Optional<Tutor> find(UUID id){
        return this.dataStore.findAllTutors().stream()
                .filter(tutor -> tutor.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Tutor> findAll(){
        return this.dataStore.findAllTutors();
    }

    @Override
    public void create(Tutor entity){
        this.dataStore.createTutor(entity);
    }

    @Override
    public void delete(UUID id){
        this.dataStore.deleteTutor(id);
    }

    @Override
    public void update(Tutor entity){
        this.dataStore.updateTutor(entity);
    }

    @Override
    public Optional<Tutor> findByEmail(String email){
        return this.dataStore.findAllTutors().stream()
                .filter(tutor -> tutor.getEmail().equals(email))
                .findFirst();
    }


}
