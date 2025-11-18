package pl.edu.pg.eti.kask.ucm.university.service.impl;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;
import pl.edu.pg.eti.kask.ucm.university.entity.University;
import pl.edu.pg.eti.kask.ucm.university.repository.api.UniversityRepository;
import pl.edu.pg.eti.kask.ucm.university.service.api.UniversityService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@LocalBean
@Stateless
@NoArgsConstructor(force = true)
public class UniversityServiceImpl implements UniversityService {

    private final UniversityRepository universityRepository;

    @Inject
    public UniversityServiceImpl(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    @Override
    public Optional<University> find(UUID id) {
        Optional<University> university = this.universityRepository.find(id);
        return university;
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
        if (this.universityRepository.find(entity.getId()).isPresent()) {
            throw new IllegalArgumentException("University already exists");
        }

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
