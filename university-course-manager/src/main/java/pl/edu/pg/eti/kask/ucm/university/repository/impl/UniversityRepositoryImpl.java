package pl.edu.pg.eti.kask.ucm.university.repository.impl;

import jakarta.enterprise.context.Dependent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import pl.edu.pg.eti.kask.ucm.university.entity.University;
import pl.edu.pg.eti.kask.ucm.university.repository.api.UniversityRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Dependent
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
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<University> query = cb.createQuery(University.class);
        Root<University> root = query.from(University.class);
        query.select(root);
        return em.createQuery(query).getResultList();
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
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<University> query = cb.createQuery(University.class);
        Root<University> root = query.from(University.class);
        query.select(root)
                .where(cb.equal(root.get("city"), city));
        return em.createQuery(query).getResultList();
    }
}
