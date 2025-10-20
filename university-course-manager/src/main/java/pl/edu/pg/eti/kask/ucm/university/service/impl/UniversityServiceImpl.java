package pl.edu.pg.eti.kask.ucm.university.service.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;
import pl.edu.pg.eti.kask.ucm.university.entity.University;
import pl.edu.pg.eti.kask.ucm.university.repository.api.UniversityRepository;
import pl.edu.pg.eti.kask.ucm.university.service.api.UniversityService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
@NoArgsConstructor(force = true)
public class UniversityServiceImpl implements UniversityService {

    private final UniversityRepository universityRepository;

    @Inject
    public UniversityServiceImpl(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    @Override
    public Optional<University> find(UUID id) {
        return this.universityRepository.find(id);
    }

    @Override
    public List<University> findAll() {
        return this.universityRepository.findAll();
    }

    @Override
    public List<University> findByCity(String city) {
        return this.universityRepository.findByCity(city);
    }

    @Override
    public void create(University entity) {
        this.universityRepository.create(entity);
    }

    @Override
    public void update(University entity) {
        this.universityRepository.update(entity);
    }

    @Override
    public void delete(UUID id) {
        this.universityRepository.delete(id);
    }
}
