package pl.edu.pg.eti.kask.ucm.course.repository.impl;

import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import pl.edu.pg.eti.kask.ucm.course.entity.Course;
import pl.edu.pg.eti.kask.ucm.course.repository.api.CourseRepository;
import pl.edu.pg.eti.kask.ucm.tutor.entity.Tutor;
import pl.edu.pg.eti.kask.ucm.university.entity.University;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RequestScoped
public class CourseRepositoryImpl implements CourseRepository {

    private EntityManager em;

    @PersistenceContext
    public void setEm(EntityManager em){
        this.em = em;
    }

    @Override
    public List<Course> findAll() {
        return this.em.createQuery("select c from Course c", Course.class).getResultList();
    }

    @Override
    public Optional<Course> find(UUID id) {
        return Optional.ofNullable(this.em.find(Course.class, id));
    }

    @Override
    public void create(Course entity) {
        this.em.persist(entity);
    }

    @Override
    public void delete(UUID id) {
        this.em.remove(this.em.find(Course.class, id));
    }

    @Override
    public void update(Course entity) {
        this.em.merge(entity);
    }

    @Override
    public List<Course> findAllByTutor(Tutor tutor) {
        return this.em.createQuery("select c from Course c where c.tutor = :tutor", Course.class)
                .setParameter("tutor", tutor)
                .getResultList();
    }

    @Override
    public List<Course> findAllByUniversity(University university) {
        return this.em.createQuery("select c from Course c where c.university = :university", Course.class)
                .setParameter("university", university)
                .getResultList();
    }
}
