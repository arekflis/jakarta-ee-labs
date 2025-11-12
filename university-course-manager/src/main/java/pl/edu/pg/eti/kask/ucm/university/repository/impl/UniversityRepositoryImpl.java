package pl.edu.pg.eti.kask.ucm.university.repository.impl;

import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import pl.edu.pg.eti.kask.ucm.university.entity.University;
import pl.edu.pg.eti.kask.ucm.university.repository.api.UniversityRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestScoped
public class UniversityRepositoryImpl implements UniversityRepository {

    private EntityManager em;

    @PersistenceContext
    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public Optional<University> find(UUID id) {
        return Optional.ofNullable(this.em.find(University.class, id));
    }

    @Override
    public List<University> findAll() {
        return this.em.createQuery("select u from University u", University.class).getResultList();
    }

    @Override
    public void create(University entity) {
        this.em.persist(entity);
    }

    @Override
    public void update(University entity) {
        this.em.merge(entity);
    }

    @Override
    public void delete(UUID id) {
        this.em.remove(this.em.find(University.class, id));
    }

    @Override
    public List<University> findByCity(String city) {
        return this.em.createQuery("select u from University u where u.city = :city", University.class)
                .setParameter("city", city)
                .getResultList();
    }
}
