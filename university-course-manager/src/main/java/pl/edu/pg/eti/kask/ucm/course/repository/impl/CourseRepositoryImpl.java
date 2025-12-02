package pl.edu.pg.eti.kask.ucm.course.repository.impl;

import jakarta.enterprise.context.Dependent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import pl.edu.pg.eti.kask.ucm.course.entity.Course;
import pl.edu.pg.eti.kask.ucm.course.model.CourseFilterModel;
import pl.edu.pg.eti.kask.ucm.course.repository.api.CourseRepository;
import pl.edu.pg.eti.kask.ucm.tutor.entity.Tutor;
import pl.edu.pg.eti.kask.ucm.university.entity.University;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Dependent
public class CourseRepositoryImpl implements CourseRepository {

    private EntityManager em;

    @PersistenceContext
    public void setEm(EntityManager em){
        this.em = em;
    }

    @Override
    public List<Course> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> query = cb.createQuery(Course.class);
        Root<Course> root = query.from(Course.class);
        query.select(root);
        return em.createQuery(query).getResultList();
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
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> query = cb.createQuery(Course.class);
        Root<Course> root = query.from(Course.class);
        query.select(root)
                .where(cb.equal(root.get("tutor"), tutor));
        return em.createQuery(query).getResultList();
    }

    @Override
    public List<Course> findAllByUniversity(University university) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> query = cb.createQuery(Course.class);
        Root<Course> root = query.from(Course.class);
        query.select(root)
                .where(cb.equal(root.get("university"), university));
        return em.createQuery(query).getResultList();
    }

    @Override
    public Optional<Course> findByIdAndTutor(UUID id, Tutor tutor) {
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Course> query = cb.createQuery(Course.class);
            Root<Course> root = query.from(Course.class);
            query.select(root)
                    .where(cb.and(
                            cb.equal(root.get("id"), id),
                            cb.equal(root.get("tutor"), tutor)
                    ));
            return Optional.of(em.createQuery(query).getSingleResult());
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }

    @Override
    public List<Course> findAllByUniversityWithFilter(University university, CourseFilterModel filter) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> query = cb.createQuery(Course.class);
        Root<Course> root = query.from(Course.class);
        query.select(root);

        List<Predicate> predicates = new ArrayList<>();
        
        predicates.add(cb.equal(root.get("university"), university));
        
        predicates.addAll(buildFilterPredicates(cb, root, filter));

        if (!predicates.isEmpty()) {
            query.where(cb.and(predicates.toArray(new Predicate[0])));
        }

        return em.createQuery(query).getResultList();
    }

    private List<Predicate> buildFilterPredicates(CriteriaBuilder cb, Root<Course> root, CourseFilterModel filter) {
        List<Predicate> predicates = new ArrayList<>();

        if (filter != null) {
            if (filter.getName() != null && !filter.getName().trim().isEmpty()) {
                predicates.add(cb.equal(root.get("name"), filter.getName()));
            }
            if (filter.getDescription() != null && !filter.getDescription().trim().isEmpty()) {
                predicates.add(cb.equal(root.get("description"), filter.getDescription()));
            }
            if (filter.getStudyType() != null) {
                predicates.add(cb.equal(root.get("studyType"), filter.getStudyType()));
            }
            if (filter.getPassingThreshold() != null) {
                predicates.add(cb.equal(root.get("passingThreshold"), filter.getPassingThreshold()));
            }
            if (filter.getSemester() != null) {
                predicates.add(cb.equal(root.get("semester"), filter.getSemester()));
            }
        }

        return predicates;
    }
}
