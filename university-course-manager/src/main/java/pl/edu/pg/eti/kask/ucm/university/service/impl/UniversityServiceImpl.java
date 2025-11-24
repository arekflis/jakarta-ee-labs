package pl.edu.pg.eti.kask.ucm.university.service.impl;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;
import pl.edu.pg.eti.kask.ucm.tutor.entity.TutorRoles;
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
    @RolesAllowed({TutorRoles.ADMIN, TutorRoles.USER})
    public Optional<University> find(UUID id) {
        Optional<University> university = this.universityRepository.find(id);
        return university;
    }

    @Override
    @RolesAllowed({TutorRoles.ADMIN, TutorRoles.USER})
    public List<University> findAll() {
        return this.universityRepository.findAll();
    }

    @Override
    @RolesAllowed({TutorRoles.ADMIN, TutorRoles.USER})
    public List<University> findByCity(String city) {
        return this.universityRepository.findByCity(city);
    }

    @Override
    @RolesAllowed(TutorRoles.ADMIN)
    public void create(University entity) {
        if (this.universityRepository.find(entity.getId()).isPresent()) {
            throw new IllegalArgumentException("University already exists");
        }

        this.universityRepository.create(entity);
    }

    @Override
    @RolesAllowed(TutorRoles.ADMIN)
    public void update(University entity) {
        this.universityRepository.update(entity);
    }

    @Override
    @RolesAllowed(TutorRoles.ADMIN)
    public void delete(UUID id) {
        this.universityRepository.delete(id);
    }
}
