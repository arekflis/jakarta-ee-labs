package pl.edu.pg.eti.kask.ucm.tutor.repository.impl;

import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import pl.edu.pg.eti.kask.ucm.tutor.entity.Tutor;
import pl.edu.pg.eti.kask.ucm.tutor.repository.api.TutorRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestScoped
public class TutorInMemoryRepository implements TutorRepository {

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
        return this.em.createQuery("select t from Tutor t", Tutor.class).getResultList();
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
        return Optional.ofNullable(this.em.createQuery("select t from Tutor t where t.email = :email", Tutor.class)
                .setParameter("email", email)
                .getSingleResult());
    }
}
