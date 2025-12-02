package pl.edu.pg.eti.kask.ucm.tutor.repository.impl;

import jakarta.enterprise.context.Dependent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import pl.edu.pg.eti.kask.ucm.tutor.entity.Tutor;
import pl.edu.pg.eti.kask.ucm.tutor.repository.api.TutorRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Dependent
public class TutorRepositoryImpl implements TutorRepository {

    private EntityManager em;

    @PersistenceContext
    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public Optional<Tutor> find(UUID id){
        return Optional.ofNullable(this.em.find(Tutor.class, id));
    }

    @Override
    public List<Tutor> findAll(){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tutor> query = cb.createQuery(Tutor.class);
        Root<Tutor> root = query.from(Tutor.class);
        query.select(root);
        return em.createQuery(query).getResultList();
    }

    @Override
    public void create(Tutor entity){
        this.em.persist(entity);
    }

    @Override
    public void delete(UUID id) {
        this.em.remove(this.em.find(Tutor.class, id));
    }

    @Override
    public void update(Tutor entity) {
        this.em.merge(entity);
    }

    @Override
    public Optional<Tutor> findByEmail(String email){
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Tutor> query = cb.createQuery(Tutor.class);
            Root<Tutor> root = query.from(Tutor.class);
            query.select(root)
                    .where(cb.equal(root.get("email"), email));
            return Optional.of(em.createQuery(query).getSingleResult());
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Tutor> findByLogin(String login){
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Tutor> query = cb.createQuery(Tutor.class);
            Root<Tutor> root = query.from(Tutor.class);
            query.select(root)
                    .where(cb.equal(root.get("login"), login));
            return Optional.of(em.createQuery(query).getSingleResult());
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }
}
