package pl.edu.pg.eti.kask.ucm.course.repository.impl;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import pl.edu.pg.eti.kask.ucm.course.entity.Course;
import pl.edu.pg.eti.kask.ucm.course.repository.api.CourseRepository;
import pl.edu.pg.eti.kask.ucm.datastore.component.DataStore;
import pl.edu.pg.eti.kask.ucm.tutor.entity.Tutor;
import pl.edu.pg.eti.kask.ucm.university.entity.University;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RequestScoped
public class CourseRepositoryImpl implements CourseRepository {

    private final DataStore dataStore;

    @Inject
    public CourseRepositoryImpl(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public List<Course> findAll() {
        return this.dataStore.findAllCourses();
    }

    @Override
    public Optional<Course> find(UUID id) {
        return this.dataStore.findAllCourses().stream()
                .filter(course -> course.getId().equals(id))
                .findFirst();
    }

    @Override
    public void create(Course entity) {
        this.dataStore.createCourse(entity);
    }

    @Override
    public void delete(UUID id) {
        this.dataStore.deleteCourse(id);
    }

    @Override
    public void update(Course entity) {
        this.dataStore.updateCourse(entity);
    }

    @Override
    public Optional<Course> findByIdAndTutor(UUID id, Tutor tutor) {
        return this.dataStore.findAllCourses().stream()
                .filter(course -> course.getId().equals(id))
                .filter(course -> course.getTutor().equals(tutor))
                .findFirst();
    }

    @Override
    public List<Course> findAllByTutor(Tutor tutor) {
        return this.dataStore.findAllCourses().stream()
                .filter(course -> tutor.equals(course.getTutor()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Course> findAllByUniversity(University university) {
        return this.dataStore.findAllCourses().stream()
                .filter(course -> university.equals(course.getUniversity()))
                .collect(Collectors.toList());
    }
}
