package pl.edu.pg.eti.kask.ucm.university.repository.impl;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import pl.edu.pg.eti.kask.ucm.datastore.component.DataStore;
import pl.edu.pg.eti.kask.ucm.university.entity.University;
import pl.edu.pg.eti.kask.ucm.university.repository.api.UniversityRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestScoped
public class UniversityInMemoryRepository implements UniversityRepository {

    private final DataStore dataStore;

    @Inject
    public UniversityInMemoryRepository(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public Optional<University> find(UUID id) {
        return this.dataStore.findAllUniversities().stream()
                .filter(university -> university.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<University> findAll() {
        return this.dataStore.findAllUniversities();
    }

    @Override
    public void create(University entity) {
        this.dataStore.createUniversity(entity);
    }

    @Override
    public void update(University entity) {
        this.dataStore.updateUniversity(entity);
    }

    @Override
    public void delete(UUID id) {
        this.dataStore.deleteUniversity(id);
    }

    @Override
    public List<University> findByCity(String city) {
        return this.dataStore.findAllUniversities().stream()
                .filter(university -> university.getCity().equals(city))
                .toList();
    }
}
