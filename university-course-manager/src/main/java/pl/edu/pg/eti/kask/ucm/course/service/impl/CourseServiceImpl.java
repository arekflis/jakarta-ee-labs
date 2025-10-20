package pl.edu.pg.eti.kask.ucm.course.service.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;
import pl.edu.pg.eti.kask.ucm.course.entity.Course;
import pl.edu.pg.eti.kask.ucm.course.repository.api.CourseRepository;
import pl.edu.pg.eti.kask.ucm.course.service.api.CourseService;
import pl.edu.pg.eti.kask.ucm.tutor.entity.Tutor;
import pl.edu.pg.eti.kask.ucm.tutor.repository.api.TutorRepository;
import pl.edu.pg.eti.kask.ucm.university.repository.api.UniversityRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
@NoArgsConstructor(force = true)
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    private final TutorRepository tutorRepository;

    private final UniversityRepository universityRepository;

    @Inject
    public CourseServiceImpl(CourseRepository courseRepository, TutorRepository tutorRepository,
                                UniversityRepository universityRepository) {
        this.courseRepository = courseRepository;
        this.tutorRepository = tutorRepository;
        this.universityRepository = universityRepository;
    }

    @Override
    public Optional<Course> find(UUID id) {
        return this.courseRepository.find(id);
    }

    @Override
    public List<Course> findAll() {
        return this.courseRepository.findAll();
    }

    @Override
    public Optional<Course> findByIdAndTutor(UUID id, Tutor tutor) {
        return this.courseRepository.findByIdAndTutor(id, tutor);
    }

    @Override
    public void create(Course entity) {
        this.courseRepository.create(entity);
    }

    @Override
    public void update(Course entity) {
        this.courseRepository.update(entity);
    }

    @Override
    public void delete(UUID id) {
        this.courseRepository.delete(id);
    }

    @Override
    public Optional<List<Course>> findAllByUniversity(UUID id) {
        return this.universityRepository.find(id)
                .map(courseRepository::findAllByUniversity);
    }

    @Override
    public Optional<List<Course>> findAllByTutor(UUID id) {
        return this.tutorRepository.find(id)
                .map(courseRepository::findAllByTutor);
    }
}
